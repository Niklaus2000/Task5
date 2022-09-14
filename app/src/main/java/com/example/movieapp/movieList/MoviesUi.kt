package com.example.movieapp.movieList

import android.view.View
import com.example.movieapp.data.entity.MoviesListItem
import com.example.movieapp.databinding.FragmentMovieListBinding
import com.example.movieapp.movieList.adapter.MoviesAdapter

interface MoviesUi {

    fun apply(binding: FragmentMovieListBinding , adapter: MoviesAdapter)

    object Empty : MoviesUi {
        override fun apply(binding: FragmentMovieListBinding , adapter: MoviesAdapter) = Unit
    }

    class LoadingUi : MoviesUi {
        override fun apply(
            binding: FragmentMovieListBinding ,
            adapter: MoviesAdapter ,
        ) {
            binding.progressBar.visibility = View.VISIBLE
        }
    }

    class ContentUi(private val items: List<MoviesListItem>) : MoviesUi {
        override fun apply(
            binding: FragmentMovieListBinding ,
            adapter: MoviesAdapter ,
        ) {
            binding.progressBar.visibility = View.GONE
            adapter.submitList(items)
        }
    }

    class ErrorUi(private val errorMessage: String) : MoviesUi {
        override fun apply(
            binding: FragmentMovieListBinding ,
            adapter: MoviesAdapter ,
        ) = with(binding) {
            progressBar.visibility = View.GONE
            binding.errorTextView.visibility = View.VISIBLE
            binding.errorTextView.text = errorMessage

        }
    }
}
