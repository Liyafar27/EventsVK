package ru.example.myfirstkotlinapp.network

import retrofit2.Call
import retrofit2.http.GET
import ru.example.myfirstkotlinapp.data.remote.RemoteEvent

interface EventApiClient {

    @GET("/method/groups.search?v=5.95&fields=description,start_date,finish_date,cover&access_token=14dc8624b7a008ac029601ba67215249b7381ffa1a5d92be8ae62f32e99e1bb7a31cb6bcdc019884e7fbb&city_id=56&q=%D0%B0&type=event&start_date=3722976&future=1&sort=0&count=500")
    fun eventsRequest(
    ): Call<RemoteEvent>}


