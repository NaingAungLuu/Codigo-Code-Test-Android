package me.naingaungluu.codetest.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import me.naingaungluu.codetest.data.models.domain.Movie
import me.naingaungluu.codetest.data.models.entities.PopularMovieEntity
import me.naingaungluu.codetest.data.models.entities.UpcomingMovieEntity
import me.naingaungluu.codetest.data.models.networkResponses.MovieListResponse
import me.naingaungluu.codetest.data.sources.local.MovieDao
import me.naingaungluu.codetest.data.sources.remote.MovieRemoteDataSource
import me.naingaungluu.codetest.ui.helpers.StatefulData
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val movieItemDao : MovieDao, private val remoteDataSource: MovieRemoteDataSource) : BaseRepository(), MovieRepository{

    // When network failed -> Error + Retry
    // When network success -> Save Local data + complete
    // When Started -> show local data
    // When local is invalid -> Error

    override fun getPopularMovies() : Flow<StatefulData<List<Movie>>> = flow {
        movieItemDao.getPopularMovies().collect { popularMovieList ->
            val movieList = popularMovieList.mapNotNull { it.movie }
            if (movieList.isNotEmpty()) emit(StatefulData.success(movieList))
            else if(movieList.isEmpty()) emit(StatefulData.error("Unable to load popular movies"))
        }
        getRemotePopularMovies(this@flow)
    }.flowOn(Dispatchers.IO)



    override fun getUpcomingMovies() : Flow<StatefulData<List<Movie>>> = flow {
        fetchRemoteUpcomingMovies(this)
        movieItemDao.getUpcomingMovies().collect { upcomingMovieList ->
            val movieList = upcomingMovieList.mapNotNull { it.movie }
            if (movieList.isNotEmpty()) emit(StatefulData.success(movieList))
            else if(movieList.isEmpty()) emit(StatefulData.error("Unable to load popular movies"))
        }
        getRemotePopularMovies(this@flow)
    }.flowOn(Dispatchers.IO)


    override fun getMovieDetails(movieId : Int) : Flow<StatefulData<Movie>> = movieItemDao.getMovieDetails(movieId)
        .mapNotNull {  StatefulData.success(it) }
        .onStart {
            val remoteData = remoteDataSource.fetchMovieDetails(movieId)
            when(remoteData.state) {
                StatefulData.DataState.SUCCESS -> movieItemDao.insertMovie(remoteData.data!!.toMovieInfoEntity()) // Data
                else -> emit(remoteData as StatefulData<Movie>)
            }
        }
        .flowOn(Dispatchers.IO)

    override fun setFavourite(movieId : Int , isFavourite : Boolean) {
        movieItemDao.setFavouriteMovie(movieId , isFavourite)
    }

    private suspend fun fetchRemoteUpcomingMovies(collector : FlowCollector<StatefulData<List<Movie>>>) {
        val remoteData = remoteDataSource.fetchUpcomingMovies()
        when (remoteData.state) {
            StatefulData.DataState.SUCCESS -> {
                val movieList = convertToMovieItemList(remoteData.data)
                saveUpcomingMovies(movieList)
                collector.emit(StatefulData.completed())
            }
            StatefulData.DataState.ERROR -> {
                collector.emit(StatefulData.error("Network failed, retrying..."))
                fetchRemoteUpcomingMovies(collector) //A Recursive call to retry
            }
            else -> collector.emit(StatefulData.loading())
        }
    }

    private suspend fun getRemotePopularMoviesNetworkFlow() : Flow<StatefulData<List<Movie>>> = flow {
        val remoteData = remoteDataSource.fetchPopularMovies()
        when (remoteData.state) {
            StatefulData.DataState.SUCCESS -> {
                val movieList = convertToMovieItemList(remoteData.data)
                savePopularMovies(movieList)
                emit(StatefulData.completed())
            }
            StatefulData.DataState.ERROR -> {
                emit(StatefulData.error("Network failed, retrying..."))
            }
            else -> emit(StatefulData.loading())
        }
    }

    private suspend fun getRemotePopularMovies(collector : FlowCollector<StatefulData<List<Movie>>>) {
        val remoteData = remoteDataSource.fetchPopularMovies()
        when (remoteData.state) {
            StatefulData.DataState.SUCCESS -> {
                val movieList = convertToMovieItemList(remoteData.data)
                savePopularMovies(movieList)
                collector.emit(StatefulData.completed())
            }
            StatefulData.DataState.ERROR -> {
                collector.emit(StatefulData.error("Network failed, retrying..."))
                getRemotePopularMovies(collector) //A Recursive call to retry
            }
            else -> collector.emit(StatefulData.loading())
        }
    }

    private fun convertToMovieItemList(response : MovieListResponse?) : List<Movie> {
        return if (response?.results == null) emptyList()
        else response.results.map { it.toMovie() }
    }

    private fun saveUpcomingMovies(data : List<Movie>) {
        movieItemDao.insertMovieItems(data)
        val upcomingMovieList = data.map {
            UpcomingMovieEntity(movieId = it.id)
        }
        movieItemDao.insertUpcomingMovies(upcomingMovieList)
    }

    private fun savePopularMovies(data : List<Movie>) {
        movieItemDao.insertMovieItems(data)
        val popularMovieList = data.map {
            PopularMovieEntity(movieId = it.id)
        }
        movieItemDao.insertPopularMovies(popularMovieList)
    }

}