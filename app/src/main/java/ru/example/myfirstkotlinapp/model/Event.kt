package ru.example.myfirstkotlinapp.model

import ru.example.myfirstkotlinapp.data.remote.Response


interface Event {

    val response: Response?

}

interface ItemEvent {
    val name: String
    val description: String
    val start_date: Long
    val finish_date: Long
    val cover: Cover?
    val photo_200: String
}

interface Cover {
    val enabled: Int
    val images: List<ImagesEvent>?
}

interface ImagesEvent {
    val url: String?
}

