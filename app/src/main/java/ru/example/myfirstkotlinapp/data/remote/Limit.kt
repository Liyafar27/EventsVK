package ru.example.myfirstkotlinapp.data.remote

import com.squareup.moshi.Json

data class Limit(
    @Json(name = "resources")
    val resources: Core
)
data class Core(
    @Json(name = "core")
    val core: Remaining
)
data class Remaining(
    @Json(name = "remaining")
    val remaining: Int
)