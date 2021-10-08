package me.naingaungluu.codetest.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.naingaungluu.codetest.repositories.MovieRepositoryImpl
import javax.inject.Inject

class HomeViewModelFactory @Inject constructor(private val movieRepository: MovieRepositoryImpl) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(movieRepository) as T
    }
}