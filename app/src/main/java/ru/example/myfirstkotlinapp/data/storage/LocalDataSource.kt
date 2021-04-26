package ru.example.myfirstkotlinapp.data.storage

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.example.myfirstkotlinapp.data.remote.RemoteGitHubRepo
import ru.example.myfirstkotlinapp.model.GitHubRepo
import ru.example.myfirstkotlinapp.model.Stargazer
import ru.example.myfirstkotlinapp.room.RepoDatabase
import java.util.*

class LocalDataSource : DataSource {
    override suspend fun getRepositories(ownerName: String, page: Int, perPage: Int)
            : List<GitHubRepo> = withContext(Dispatchers.IO) {
        RepoDatabase.INSTANCE.getGitHubRepo(ownerName.trim().toLowerCase(Locale.ENGLISH))
    }

    override suspend fun getStargazers(
        repoRecycle: String, ownerRecycle: String, page: Int,
        perPage: Int
    ): List<Stargazer> = withContext(Dispatchers.IO) {
        RepoDatabase.INSTANCE.getStargazers(repoRecycle)
    }

    suspend fun getAllRepositories(): List<GitHubRepo> {
        return RepoDatabase.INSTANCE.getAllRepos()
    }



    suspend fun saveRepositories(list: List<GitHubRepo>) {
        RepoDatabase.INSTANCE.saveGitHubRepo(list)
    }


    suspend fun saveStargazers(list: List<Stargazer>) {
        RepoDatabase.INSTANCE.saveStargazer(list)

    }

}

