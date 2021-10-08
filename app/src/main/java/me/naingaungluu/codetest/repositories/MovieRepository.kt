package me.naingaungluu.codetest.repositories

import kotlinx.coroutines.flow.Flow
import me.naingaungluu.codetest.data.models.domain.Movie
import me.naingaungluu.codetest.ui.helpers.StatefulData

interface MovieRepository {
    fun getPopularMovies() : Flow<StatefulData<List<Movie>>>
    fun getUpcomingMovies() : Flow<StatefulData<List<Movie>>>
    fun getMovieDetails(movieId : Int) : Flow<StatefulData<Movie>>
    fun setFavourite(movieId : Int , isFavourite : Boolean)
}