package me.naingaungluu.codetest.ui.screens.movieDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.layout_toolbar_movie_details.view.*
import me.naingaungluu.codetest.databinding.FragmentMovieDetailsBinding
import me.naingaungluu.codetest.ui.helpers.BaseFragment
import me.naingaungluu.codetest.ui.helpers.StatefulData

@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment<MovieDetailsViewModel>() {

    private var _binding :  FragmentMovieDetailsBinding? = null
    private val binding : FragmentMovieDetailsBinding
    get() = _binding!!

    private val viewModel : MovieDetailsViewModel by viewModels()


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        //Inflate Transition Animation
//        val animation = TransitionInflater.from(requireContext()).inflateTransition(
//            android.R.transition.move
//        )
//        //Setup Shared Element transition property
//        sharedElementEnterTransition = animation
//        sharedElementReturnTransition = animation
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailsBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun setupUi() {
        viewModel.movieDetails.observe(viewLifecycleOwner) { movieDetails ->
            when(movieDetails.state) {
                StatefulData.DataState.SUCCESS -> {
                    binding.layoutToolbar.isLoading = false
                    binding.data = movieDetails.data
                }
                StatefulData.DataState.ERROR -> {
                    binding.layoutToolbar.isLoading = false
                    showSnackBar(movieDetails.message.toString())
                }
                StatefulData.DataState.LOADING -> {
                    binding.layoutToolbar.isLoading = true
                }
            }
        }
    }

    override fun setupListeners() {
        with(binding.layoutDescription.btnReadMore) {
            setOnClickListener {
                binding.layoutDescription.descriptionExpanded = !(binding.layoutDescription.descriptionExpanded ?: false)
            }
        }
        binding.layoutToolbar.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.layoutToolbar.btnFavourite.setOnClickListener {
            viewModel.onTapFavourite()
        }
    }

}