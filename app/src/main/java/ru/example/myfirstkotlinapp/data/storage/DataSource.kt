package ru.example.myfirstkotlinapp.data.storage

import ru.example.myfirstkotlinapp.model.Event

interface DataSource {

    suspend fun  getEvent(): Event


}



