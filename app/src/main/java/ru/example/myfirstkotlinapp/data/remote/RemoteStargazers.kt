package ru.example.myfirstkotlinapp.data.remote

import androidx.room.Ignore
import com.squareup.moshi.Json
import ru.example.myfirstkotlinapp.model.Stargazer
import java.util.*

data class RemoteStargazer(

    @Json(name = "user")
    override val user: RemoteUser,

    @Json(name = "created_at")
    override val starred_at: Date

) : Stargazer {

    @Ignore
    override lateinit var repoName: String






}