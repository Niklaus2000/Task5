package com.example.movieapp.data.entity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoviesResponse(
    @Json(name = "page")
    val page: Int? ,
    @Json(name = "results")
    val results: List<MoviesListItem>,
    @Json(name = "total_pages")
    val totalPages: Int? ,
    @Json(name = "total_results")
    val totalResults: Int?
)