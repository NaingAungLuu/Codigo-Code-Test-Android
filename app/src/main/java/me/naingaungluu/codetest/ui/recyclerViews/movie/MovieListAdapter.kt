package me.naingaungluu.codetest.ui.recyclerViews.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import me.naingaungluu.codetest.data.models.domain.Movie
import me.naingaungluu.codetest.databinding.ItemMovieBinding
import me.naingaungluu.codetest.ui.recyclerViews.BaseAdapter

class MovieListAdapter(private val delegate : MovieItemDelegate ) : BaseAdapter<MovieViewHolder , Movie>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemMovieBinding.inflate(layoutInflater , parent , false)
        return MovieViewHolder(itemBinding, delegate)
    }
}