package me.naingaungluu.codetest.ui.screens.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.naingaungluu.codetest.data.models.domain.Movie
import me.naingaungluu.codetest.repositories.MovieRepository
import me.naingaungluu.codetest.repositories.MovieRepositoryImpl
import me.naingaungluu.codetest.ui.helpers.BaseViewModel
import me.naingaungluu.codetest.ui.helpers.SingleLiveEvent
import me.naingaungluu.codetest.ui.helpers.StatefulData
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieRepository: MovieRepository) : BaseViewModel() {

    val navFlow : SingleLiveEvent<NavDirections> = SingleLiveEvent()

    private val _popularMovies : MutableLiveData<StatefulData<List<Movie>>> = MutableLiveData(StatefulData.loading())
    val popularMovies : LiveData<StatefulData<List<Movie>>> = _popularMovies

    private val _upcomingMovies : MutableLiveData<StatefulData<List<Movie>>> = MutableLiveData(StatefulData.loading())
    val upcomingMovies : LiveData<StatefulData<List<Movie>>> = _upcomingMovies

    init {
        viewModelScope.launch {
            //Fetching network calls in parallel
            val job1 = async { fetchUpcomingMovies() }
            val jobs = async { fetchPopularMovies() }
            job1.await()
            jobs.await()
        }
    }

    private suspend fun fetchPopularMovies() {
        movieRepository.getPopularMovies()
            .collect {
                _popularMovies.postValue(it)
            }
    }

    private suspend fun fetchUpcomingMovies() {
        movieRepository.getUpcomingMovies()
            .collect{
                _upcomingMovies.postValue(it)
            }
    }

    fun onTapMovieItem(movieId: Int) {
        navFlow.postValue(HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(movieId))
    }

    fun onTapFavourite(movie: Movie) {
        movieRepository.setFavourite(movie.id , !movie.isFavourite)
    }

}