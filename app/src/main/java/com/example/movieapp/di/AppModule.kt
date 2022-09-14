package com.example.movieapp.di

import android.content.Context
import com.example.movieapp.core.Dispatchers
import com.example.movieapp.core.HandleApiRequest
import com.example.movieapp.core.ProvideResources
import com.example.movieapp.data.api.MoviesService
import com.example.movieapp.data.repository.MoviesRepositoryImpl
import com.example.movieapp.domain.repository.MoviesRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    @Provides
//    @Singleton
//    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
//        level = HttpLoggingInterceptor.Level.BODY
//    }
//
//    @Provides
//    @Singleton
//    fun provideOkHttpClient(
//        httpLoggingInterceptor: HttpLoggingInterceptor ,
//    ): OkHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
//        .callTimeout(15 , TimeUnit.SECONDS)
//        .writeTimeout(15 , TimeUnit.SECONDS)
//        .readTimeout(15 , TimeUnit.SECONDS)
//        .connectTimeout(15 , TimeUnit.SECONDS)
//        .retryOnConnectionFailure(true).build()
//
//    @Provides
//    @Singleton
//    fun provideRetrofitClient(okHttpClient: OkHttpClient): Retrofit =
//        Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/").addConverterFactory(
//            MoshiConverterFactory.create(
//                Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
//            )
//        ).client(okHttpClient).build()
//
//    @Provides
//    @Singleton
//    fun provideRetrofitCurrency(retrofitClient: Retrofit): MoviesService =
//        retrofitClient.create(MoviesService::class.java)
//
//    @Provides
//    fun provideResources(@ApplicationContext context: Context): ProvideResources =
//        ProvideResources.Base(context)
//
//    @Provides
//    fun provideMoviesRepository(
//        moviesService: MoviesService ,
//        provideResources: ProvideResources ,
//    ): MoviesRepository = MoviesRepositoryImpl(moviesService , provideResources)
@Provides
@Singleton
fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
        .callTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .connectTimeout(15, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true).build()

    @Provides
    @Singleton
    fun provideRetrofitClient(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/").addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
            )
        ).client(okHttpClient).build()

    @Provides
    @Singleton
    fun provideRetrofitCurrency(retrofitClient: Retrofit): MoviesService =
        retrofitClient.create(MoviesService::class.java)

    @Provides
    fun provideResources(@ApplicationContext context: Context): ProvideResources =
        ProvideResources.Base(context)

    @Provides
    fun provideMoviesRepository(
        moviesService: MoviesService,
        handleApiRequest: HandleApiRequest
    ): MoviesRepository = MoviesRepositoryImpl(moviesService, handleApiRequest)

    @Provides
    fun provideDispatchers(): Dispatchers = Dispatchers.Base()

    @Provides
    fun provideBaseApiRequest(provideResources: ProvideResources): HandleApiRequest =
        HandleApiRequest.Base(provideResources)
}