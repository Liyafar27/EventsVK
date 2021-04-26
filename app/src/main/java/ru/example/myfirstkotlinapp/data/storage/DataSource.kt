package ru.example.myfirstkotlinapp.data.storage

import ru.example.myfirstkotlinapp.model.GitHubRepo
import ru.example.myfirstkotlinapp.model.Stargazer

interface DataSource {

    suspend fun getRepositories(ownerName: String, page: Int, perPage: Int): List<GitHubRepo>

    suspend fun getStargazers(
        repoRecycle: String, ownerRecycle: String, page: Int, perPage: Int
    ): List<Stargazer>


}



