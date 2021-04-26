package ru.example.myfirstkotlinapp.data.storage

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.example.myfirstkotlinapp.data.remote.Limit
import ru.example.myfirstkotlinapp.data.remote.RemoteGitHubRepo
import ru.example.myfirstkotlinapp.model.GitHubRepo
import ru.example.myfirstkotlinapp.model.Stargazer
import ru.example.myfirstkotlinapp.network.APIClient
import ru.example.myfirstkotlinapp.network.GitHubClient
import ru.example.myfirstkotlinapp.network.StargazersInterface
import java.util.*

class RemoteDataSource : DataSource {

    private val repoApi = APIClient.retrofit.create(GitHubClient::class.java)
    private val stargazerApi = APIClient.retrofit.create(StargazersInterface::class.java)


    override suspend fun getRepositories(ownerName: String, page: Int, perPage: Int)
            : List<GitHubRepo> = withContext(Dispatchers.IO) {
        @Suppress("BlockingMethodInNonBlockingContext")
        repoApi
            .reposForUser(ownerName.trim().toLowerCase(Locale.ENGLISH), page, perPage)
            .execute()
            .body() ?: emptyList()

    }

    override suspend fun getStargazers(
        repoRecycle: String, ownerRecycle: String, page: Int,
        perPage: Int
    ): List<Stargazer> = withContext(Dispatchers.IO) {
        @Suppress("BlockingMethodInNonBlockingContext")
        stargazerApi
            .stargazersForRepo(ownerRecycle, repoRecycle, page, perPage)
            .execute()
            .body()
            ?.apply {
                forEach { it.repoName = repoRecycle }
            }
            ?: emptyList()
    }


    suspend fun getRepo(ownerName: String, repoName: String): GitHubRepo? =
        withContext(Dispatchers.IO) {
            @Suppress("BlockingMethodInNonBlockingContext")
            repoApi
                .getRepos(ownerName, repoName.trim().toLowerCase(Locale.ENGLISH))
                .execute()
                .body()

        }

}