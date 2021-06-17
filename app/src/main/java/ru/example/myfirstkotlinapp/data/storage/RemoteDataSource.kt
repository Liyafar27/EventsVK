package ru.example.myfirstkotlinapp.data.storage

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.example.myfirstkotlinapp.model.Event
import ru.example.myfirstkotlinapp.network.APIClient
import ru.example.myfirstkotlinapp.network.EventApiClient

class RemoteDataSource : DataSource {

    private val eventApi = APIClient.retrofit.create(EventApiClient::class.java)


    override suspend fun getEvent()
            : Event = withContext(Dispatchers.IO) {
        @Suppress("BlockingMethodInNonBlockingContext")
        eventApi.eventsRequest()
            .execute()
            .body()!!

    }

}