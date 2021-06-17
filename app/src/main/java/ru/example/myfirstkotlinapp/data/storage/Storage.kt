package ru.example.myfirstkotlinapp.data.storage

import ru.example.myfirstkotlinapp.model.Event

class Storage(private val remoteSource: RemoteDataSource) {

    companion object {

        val instance = Storage(RemoteDataSource())

    }


    suspend fun getRequestEvent(): Event {
        val listEvents: Event = remoteSource.getEvent()
        return listEvents

    }
}