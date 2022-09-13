package com.example.movieapp.data.api

import com.example.movieapp.data.entity.MoviesResponse
import com.example.movieapp.data.entity.TokenResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {
    @GET("movie/top_rated?api_key=843c612d1207fdec63f0e6a5fd426d68")
    suspend fun getTopRatedMovies(): Response<MoviesResponse>
    @GET("authentication/token/new?api_key=843c612d1207fdec63f0e6a5fd426d68")
    suspend fun getRequestToken(): Response<TokenResponse>
}