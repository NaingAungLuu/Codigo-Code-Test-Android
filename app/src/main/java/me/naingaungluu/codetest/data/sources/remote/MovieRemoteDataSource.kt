package me.naingaungluu.codetest.data.sources.remote

import me.naingaungluu.codetest.data.models.networkResponses.MovieDetailResponse
import me.naingaungluu.codetest.data.models.networkResponses.MovieListResponse
import me.naingaungluu.codetest.ui.helpers.StatefulData
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(private val api : TheMovieDbApi) : RemoteDataSource() {

    suspend fun fetchPopularMovies() : StatefulData<MovieListResponse> = getResponse(errorMessage = "Unable to fetch Popular Movies") {
        api.getPopularMovies()
    }

    suspend fun fetchUpcomingMovies() : StatefulData<MovieListResponse> = getResponse("Unable to fetch Upcoming Movies") {
        api.getUpcomingMovies()
    }

    suspend fun fetchMovieDetails(movieId : Int) : StatefulData<MovieDetailResponse> = getResponse(errorMessage = "Unable to fetch Movie Details") {
        api.getMovie(movieId)
    }

}