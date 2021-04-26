package ru.example.myfirstkotlinapp.data.storage

import ru.example.myfirstkotlinapp.data.remote.Limit
import ru.example.myfirstkotlinapp.model.GitHubRepo
import ru.example.myfirstkotlinapp.model.Stargazer
import java.io.IOException

class Storage(
    private val remoteSource: RemoteDataSource,
    private val localSource: LocalDataSource
) {

    companion object {

        val instance = Storage(RemoteDataSource(), LocalDataSource())

    }

    suspend fun getStorageRepo(repoName: String, page: Int, perPage: Int): List<GitHubRepo> {
        var listRepositories: List<GitHubRepo>
        try {
            listRepositories = remoteSource.getRepositories(repoName, page, perPage)
            localSource.saveRepositories(listRepositories)

        } catch (exception: IOException) {
            listRepositories = localSource.getRepositories(repoName, page, perPage)
        }
        return listRepositories
    }

    suspend fun getStorageStargazers(repoRecycle: String, ownerRecycle: String, page: Int, perPage: Int
    ): List<Stargazer> {
        var listStargazers: List<Stargazer>
        try {
            listStargazers = remoteSource.getStargazers(repoRecycle, ownerRecycle, page, perPage)
            localSource.saveStargazers(listStargazers)

        } catch (exception: IOException) {
            listStargazers = localSource.getStargazers(repoRecycle, ownerRecycle, page, perPage)
        }
        return listStargazers
    }
    suspend fun getLocalRepo(): List<GitHubRepo> {
      return localSource.getAllRepositories()
    }


    suspend fun getChangedRepositories(): List<GitHubRepo> {
        val localRepositories = localSource.getAllRepositories()
        return localRepositories.mapNotNull {
//            val remoteRepo = remoteSource.getRepo("qwerty", "qwerty")
            val remoteRepo = remoteSource.getRepo(it.owner.login, it.name)

            if (remoteRepo!!.stargazers_count != it.stargazers_count) remoteRepo else null
        }
            .also {
                localSource.saveRepositories(it)

            }

    }

    suspend fun getLimitRemaining(): Limit {
        return getLimitRemaining()
    }}


