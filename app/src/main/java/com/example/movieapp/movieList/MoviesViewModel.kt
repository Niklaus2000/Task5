package com.example.movieapp.movieList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.core.Dispatchers
import com.example.movieapp.core.HandleResponse
import com.example.movieapp.domain.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository ,
    dispatcher: Dispatchers
) : ViewModel() {

    private val _listOfMoviesState = MutableStateFlow<MoviesUi>(MoviesUi.Empty)
    val listOfMoviesState = _listOfMoviesState.asStateFlow()



    init {
        dispatcher.launchBackground(viewModelScope) {
            moviesRepository.fetchMovies().collectLatest {
                val moviesData = when (it) {
                    is HandleResponse.Loading -> MoviesUi.LoadingUi()
                    is HandleResponse.Success -> MoviesUi.ContentUi(it.data.results)
                    is HandleResponse.Error -> MoviesUi.ErrorUi(it.message)
                }
                _listOfMoviesState.value = moviesData
            }
        }
    }

}

