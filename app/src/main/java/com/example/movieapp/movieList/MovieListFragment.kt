package com.example.movieapp.movieList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.databinding.FragmentMovieListBinding
import com.example.movieapp.movieList.adapter.MoviesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MoviesViewModel by viewModels()
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle? ,
    ): View? {
        _binding = FragmentMovieListBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)

        initAdapter()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.listOfMoviesState.collect {
                it.apply(binding , moviesAdapter)
            }
        }
    }

    private fun initAdapter() {
        moviesAdapter = MoviesAdapter()
        binding.rvMovies.adapter = moviesAdapter
        binding.rvMovies.layoutManager = LinearLayoutManager(requireContext())
    }


}