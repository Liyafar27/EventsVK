package ru.example.myfirstkotlinapp.network

import retrofit2.Call
import retrofit2.http.GET

import retrofit2.http.Path
import retrofit2.http.Query
import ru.example.myfirstkotlinapp.data.remote.Limit
import ru.example.myfirstkotlinapp.data.remote.RemoteGitHubRepo

interface GitHubClient {

    @GET("/users/{user}/repos?")
    fun reposForUser(
        @Path("user") user: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<List<RemoteGitHubRepo>>

    @GET("/repos/{owner}/{reponame}?")
    fun getRepos(
        @Path("owner") ownerName: String,
        @Path("reponame") repoName: String
        ): Call<RemoteGitHubRepo>

    @GET("https://api.github.com/rate_limit")
     suspend fun getLimitRemaining(): Limit


}
