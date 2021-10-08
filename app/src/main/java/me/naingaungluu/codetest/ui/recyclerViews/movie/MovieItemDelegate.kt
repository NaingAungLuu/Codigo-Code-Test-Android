package me.naingaungluu.codetest.ui.recyclerViews.movie

import me.naingaungluu.codetest.data.models.domain.Movie

interface MovieItemDelegate {
    fun onTapMovieItem(movieId : Int)
    fun onTapFavourite(movie: Movie)
}