package com.example.movieapp.movieList

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.core.HandleResponse
import com.example.movieapp.data.entity.MoviesListItem
import com.example.movieapp.databinding.FragmentMovieListBinding
import com.example.movieapp.domain.repository.MoviesRepository
import com.example.movieapp.movieList.adapter.MoviesAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository ,
) : ViewModel() {

    private val _listOfMoviesState = MutableStateFlow<MoviesUi>(MoviesUi.Empty)
    val listOfMoviesState = _listOfMoviesState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            moviesRepository.fetchMovies().collectLatest {
                val moviesData = when (it) {
                    is HandleResponse.Loading -> MoviesUi.LoadingUi()
                    is HandleResponse.Success -> MoviesUi.ContentUi(it.data)
                    is HandleResponse.Error -> MoviesUi.ErrorUi(it.message)
                }
                _listOfMoviesState.value = moviesData
            }
        }
    }
    init {
        viewModelScope.launch(Dispatchers.IO) {
            moviesRepository.fetchRequestToken().collectLatest {

            }
        }
    }
}

interface MoviesUi {

    //polymorphism
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


