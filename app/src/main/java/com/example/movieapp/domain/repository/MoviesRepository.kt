package com.example.movieapp.domain.repository

import com.example.movieapp.core.HandleResponse
import com.example.movieapp.data.entity.MoviesResponse
import com.example.movieapp.data.entity.TokenListItem
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun fetchMovies(): Flow<HandleResponse<MoviesResponse>>
    suspend fun fetchRequestToken(): Flow<HandleResponse<TokenListItem>>

}