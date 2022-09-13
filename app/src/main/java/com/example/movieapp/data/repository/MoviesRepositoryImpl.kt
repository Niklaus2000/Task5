package com.example.movieapp.data.repository

import com.example.movieapp.R
import com.example.movieapp.core.HandleResponse
import com.example.movieapp.core.ProvideResources
import com.example.movieapp.data.api.MoviesService
import com.example.movieapp.data.entity.MoviesListItem
import com.example.movieapp.data.entity.TokenListItem
import com.example.movieapp.domain.repository.MoviesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.net.UnknownHostException
import javax.inject.Inject
import kotlin.Exception

class MoviesRepositoryImpl @Inject constructor(
    private val moviesService: MoviesService ,
    private val provideResources: ProvideResources ,
    private val backgroundDispatcher: CoroutineDispatcher = Dispatchers.IO ,
) : MoviesRepository {

    override suspend fun fetchMovies(): Flow<HandleResponse<List<MoviesListItem>>> = flow {
        emit(HandleResponse.Loading())
        try {
            val service = moviesService.getTopRatedMovies()
            val body = service.body()
            emit(HandleResponse.Success(body!!.results))
        } catch (noInternet: UnknownHostException) {
            emit(HandleResponse.Error(provideResources.string(R.string.no_connection)))
        } catch (e: Exception) {
            emit(HandleResponse.Error(provideResources.string(R.string.unexpected_error)))
        }
    }.flowOn(backgroundDispatcher)

    override suspend fun fetchRequestToken(): Flow<HandleResponse<List<TokenListItem>>> = flow  {
        emit(HandleResponse.Loading())
        try {
            val service = moviesService.getRequestToken()
            val body = service.body()
            emit(HandleResponse.Success(body!!.results))
        } catch (noInternet: UnknownHostException) {
            emit(HandleResponse.Error(provideResources.string(R.string.no_connection)))
        } catch (e: Exception) {
            emit(HandleResponse.Error(provideResources.string(R.string.unexpected_error)))
        }
    }
}