package com.example.movieapp.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.movieapp.core.Dispatchers
import com.example.movieapp.core.HandleResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository ,
    private val dispatchers: Dispatchers ,
) : ViewModel() {

    private val _userToken = MutableStateFlow<String>("")
    val userToken = _userToken.asStateFlow()


    init {
        dispatchers.launchBackground(viewModelScope) {
            moviesRepository.fetchRequestToken().collectLatest {
                when (it) {
                    is HandleResponse.Loading -> {}
                    is HandleResponse.Success -> _userToken.value = it.data.requestToken
                    is HandleResponse.Error -> { Log.d("token" , it.message)

                    }
                }
            }
        }


    }
}