package com.example.movieapp.movieList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.base.BaseFragment
import com.example.movieapp.databinding.FragmentLoginBinding
import com.example.movieapp.databinding.FragmentMovieListBinding
import com.example.movieapp.login.LoginViewModel
import com.example.movieapp.movieList.adapter.MoviesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieListFragment : BaseFragment<MoviesViewModel , FragmentMovieListBinding>(FragmentMovieListBinding::inflate) {


    override val viewModel: MoviesViewModel by viewModels()
    private lateinit var moviesAdapter: MoviesAdapter



    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)

        initAdapter()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.listOfMoviesState.collect {
                it.apply(binding,moviesAdapter)
            }
        }
    }

    private fun initAdapter(): Unit = with(binding) {
        moviesAdapter = MoviesAdapter()
        rvMovies.adapter = moviesAdapter
        rvMovies.layoutManager = LinearLayoutManager(requireContext())
    }


}