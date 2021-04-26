package ru.example.myfirstkotlinapp.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import ru.example.myfirstkotlinapp.data.remote.RemoteStargazer


interface StargazersInterface {
    @Headers("Accept: application/vnd.github.v3.star+json")
    @GET("/repos/{owner}/{repo}/stargazers?")

    fun stargazersForRepo(
        @Path("owner") owner: String, @Path("repo") repo: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<List<RemoteStargazer>>
}