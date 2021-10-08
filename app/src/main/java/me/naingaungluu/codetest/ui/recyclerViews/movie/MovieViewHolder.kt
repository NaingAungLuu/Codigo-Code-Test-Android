package me.naingaungluu.codetest.ui.recyclerViews.movie

import kotlinx.android.synthetic.main.item_movie.view.*
import me.naingaungluu.codetest.data.models.domain.Movie
import me.naingaungluu.codetest.databinding.ItemMovieBinding
import me.naingaungluu.codetest.ui.recyclerViews.BaseViewHolder

class MovieViewHolder(private val binding : ItemMovieBinding, private val delegate : MovieItemDelegate) : BaseViewHolder<Movie>(binding.root) {

    init {
        binding.cvMovieCover.setOnClickListener {
            mData?.let {
                delegate.onTapMovieItem(it.id)
            }
        }
        binding.btnFavourite.setOnClickListener {
            mData?.let {
                delegate.onTapFavourite(it)
            }
        }
    }

    override fun setData(data: Movie) {
        binding.data = data
    }
}