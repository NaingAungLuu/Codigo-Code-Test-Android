package me.naingaungluu.codetest.ui.screens.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import me.naingaungluu.codetest.data.models.domain.Movie
import me.naingaungluu.codetest.databinding.FragmentHomeBinding
import me.naingaungluu.codetest.ui.helpers.BaseFragment
import me.naingaungluu.codetest.ui.helpers.StatefulData
import me.naingaungluu.codetest.ui.recyclerViews.movie.MovieItemDelegate
import me.naingaungluu.codetest.ui.recyclerViews.movie.MovieListAdapter

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel>() , MovieItemDelegate {

    private val viewModel by viewModels<HomeViewModel>()

    private var _binding : FragmentHomeBinding? = null
    private val binding : FragmentHomeBinding
        get() = _binding!!

    private val upcomingMoviesAdapter : MovieListAdapter by lazy { MovieListAdapter(this) }
    private val popularMoviesAdapter : MovieListAdapter by lazy { MovieListAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater , container ,false)
        return binding.root
    }

    override fun setupUi() {
        setupNavigator()
        setupRecyclerViews()
        setupDataBinding()
    }

    override fun setupListeners() {
    }

    private fun setupNavigator() {
        viewModel.navFlow.observe(viewLifecycleOwner) { direction ->
            direction?.let {
                findNavController().navigate(it)
            }
        }
    }

    private fun setupDataBinding() {
        viewModel.popularMovies.observe(viewLifecycleOwner) { popularMovies ->
            when(popularMovies.state) {
                StatefulData.DataState.LOADING -> binding.layoutPopularMovies.isLoading = true
                StatefulData.DataState.ERROR -> {
                    showSnackBar(popularMovies.message.toString())
                }
                StatefulData.DataState.SUCCESS -> {
                    popularMoviesAdapter.setNewData(popularMovies.data!!)
                }
                StatefulData.DataState.COMPLETED -> {
                    binding.layoutPopularMovies.isLoading = false
                }
            }
        }
        viewModel.upcomingMovies.observe(viewLifecycleOwner) { upcomingMovies ->
            when(upcomingMovies.state) {
                StatefulData.DataState.LOADING -> {
                    Log.d("CodeTest", "LoadingCalled")
                    binding.layoutUpcomingMovies.isLoading = true
                }
                StatefulData.DataState.ERROR -> {
                    showSnackBar(upcomingMovies.message.toString())
                }
                StatefulData.DataState.SUCCESS -> {
                    upcomingMoviesAdapter.setNewData(upcomingMovies.data!!)
                }
                StatefulData.DataState.COMPLETED -> {
                    Log.d("CodeTest" , "Completed upcoming Network call")
                    binding.layoutUpcomingMovies.isLoading = false
                }
            }
        }
    }

    private fun setupRecyclerViews() {
        with(binding.layoutUpcomingMovies.rvMovies){
            adapter = upcomingMoviesAdapter
            layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false)
        }
        with(binding.layoutPopularMovies.rvMovies){
            adapter = popularMoviesAdapter
            layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false)
        }
    }

    override fun onTapMovieItem(movieId: Int) {
        viewModel.onTapMovieItem(movieId)
    }

    override fun onTapFavourite(movie: Movie) {
        viewModel.onTapFavourite(movie)
    }

}