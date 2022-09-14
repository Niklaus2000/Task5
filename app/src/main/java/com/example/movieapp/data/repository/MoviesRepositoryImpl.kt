package com.example.movieapp.data.repository

import com.example.movieapp.core.HandleApiRequest
import com.example.movieapp.core.HandleResponse
import com.example.movieapp.data.api.MoviesService
import com.example.movieapp.data.entity.MoviesResponse
import com.example.movieapp.data.entity.TokenListItem
import com.example.movieapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesService: MoviesService ,
    private val handleApiRequest: HandleApiRequest
) : MoviesRepository {

    override suspend fun fetchMovies(): Flow<HandleResponse<MoviesResponse>> =
        handleApiRequest.handleApiRequest {
            moviesService.getTopRatedMovies()
        }


    override suspend fun fetchRequestToken(): Flow<HandleResponse<TokenListItem>> =
        handleApiRequest.handleApiRequest {
            moviesService.getRequestToken()
        }


}