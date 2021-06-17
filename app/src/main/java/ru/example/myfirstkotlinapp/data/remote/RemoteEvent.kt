package ru.example.myfirstkotlinapp.data.remote

import com.squareup.moshi.Json
import ru.example.myfirstkotlinapp.model.*

data class Response(
    @Json(name = "count")
    override val count: Int,
    @Json(name = "items")
    override val items: List<RemoteItemEvent>
) : ResponseEvent

data class RemoteEvent(
    @Json(name = "response")
    override val response: Response?

) : Event

data class RemoteItemEvent(
    @Json(name = "name")
    override val name: String,
    @Json(name = "description")
    override val description: String,
    @Json(name = "start_date")
    override val start_date: Long,
    @Json(name = "finish_date")
    override val finish_date: Long,
    @Json(name = "cover")
    override val cover: RemoteCover?,
    @Json(name = "photo_200")
    override val photo_200: String

) : ItemEvent

data class RemoteCover(

    @Json(name = "enabled")
    override val enabled: Int,
    @Json(name = "images")
    override val images: List<RemoteImagesEvent>? = null
) : Cover
data class RemoteImagesEvent(

    @Json(name = "url")
    override val url: String?
) : ImagesEvent

