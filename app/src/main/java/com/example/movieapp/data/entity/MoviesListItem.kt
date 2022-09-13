package com.example.movieapp.data.entity

import com.bumptech.glide.Glide
import com.example.movieapp.databinding.MovieListItemBinding
import com.example.movieapp.utils.Constants.Companion.IMAGE_PATH
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.lang.System.load
import java.util.ServiceLoader.load

@JsonClass(generateAdapter = true)
data class MoviesListItem(
    @Json(name = "adult")
    private val adult: Boolean? ,
    @Json(name = "backdrop_path")
    private val backdropPath: String? ,
    @Json(name = "genre_ids")
    private val genreIds: List<Int?>? ,
    @Json(name = "id")
    private val id: Int? ,
    @Json(name = "original_language")
    private val originalLanguage: String? ,
    @Json(name = "original_title")
    private val originalTitle: String? ,
    @Json(name = "overview")
    private val overview: String? ,
    @Json(name = "popularity")
    private val popularity: Double? ,
    @Json(name = "poster_path")
    private val posterPath: String? ,
    @Json(name = "release_date")
    private val releaseDate: String? ,
    @Json(name = "title")
    private val title: String? ,
    @Json(name = "video")
    private val video: Boolean? ,
    @Json(name = "vote_average")
    private val voteAverage: Double? ,
    @Json(name = "vote_count")
    private val voteCount: Int? ,
) {
    fun bind(binding: MovieListItemBinding) {
        binding.titleTextView.text = title
        binding.dateTextView.text = releaseDate
        binding.ratingTextView.text = voteAverage.toString()
        val posterUrl = "$IMAGE_PATH$posterPath"
        Glide.with(binding.root.context).load(posterUrl)
            .into(binding.posterImageView)



    }
}