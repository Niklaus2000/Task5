package com.example.movieapp.data.entity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TokenListItem(
    @Json(name = "expires_at")
    val expiresAt: String?,
    @Json(name = "request_token")
    val requestToken: String?,
    @Json(name = "success")
    val success: Boolean?
)