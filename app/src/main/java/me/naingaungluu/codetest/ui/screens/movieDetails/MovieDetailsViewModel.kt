package me.naingaungluu.codetest.ui.screens.movieDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.naingaungluu.codetest.data.models.domain.Movie
import me.naingaungluu.codetest.repositories.MovieRepository
import me.naingaungluu.codetest.repositories.MovieRepositoryImpl
import me.naingaungluu.codetest.ui.helpers.BaseViewModel
import me.naingaungluu.codetest.ui.helpers.StatefulData
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    state: SavedStateHandle,
    private val movieRepository: MovieRepository
) : BaseViewModel() {

    private var movieId : Int = -1

    private val _movieDetails : MutableLiveData<StatefulData<Movie>> = MutableLiveData(StatefulData.loading())
    val movieDetails : LiveData<StatefulData<Movie>> = _movieDetails

    init {
        viewModelScope.launch {
            movieId = state.get<Int>("movieId")!!.toInt()
            fetchMovieDetails()
        }
    }

    private fun fetchMovieDetails() {
        viewModelScope.launch {
            movieRepository.getMovieDetails(movieId).collect {
                _movieDetails.postValue(it)
            }
        }
    }

    fun onTapFavourite() {
        val isFavourite = _movieDetails.value?.data?.isFavourite ?: false
        movieRepository.setFavourite(movieId , !isFavourite)
    }

}