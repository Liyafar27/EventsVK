package ru.example.myfirstkotlinapp.data.remote

import com.squareup.moshi.Json
import ru.example.myfirstkotlinapp.model.User

data class RemoteUser(
    @Json(name = "login")
    override val login: String,
    @Json(name = "avatar_url")
    override val avatar_url: String
) : User

