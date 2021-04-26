package ru.example.myfirstkotlinapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.example.myfirstkotlinapp.data.local.*
import ru.example.myfirstkotlinapp.model.GitHubRepo
import ru.example.myfirstkotlinapp.model.Stargazer

@Database(
    entities = [GitHubRepoTable::class, LocalUser::class, StargazerRepoTable::class],
    version = 22,
    exportSchema = false
)
abstract class RepoDatabase : RoomDatabase() {

    abstract fun repoDao(): RepoDao

    abstract fun stargazersDao(): StargazersDao

    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        lateinit var INSTANCE: RepoDatabase
        fun initDatabase(context: Context): RepoDatabase {
            synchronized(RepoDatabase::class) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RepoDatabase::class.java,
                    "repo_database22"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    suspend fun saveGitHubRepo(repos: List<GitHubRepo>) {
            val repoList = repos.map(::GitHubRepoTable)
            val userList = repos.map { it.owner }.map(::LocalUser)
            repoDao().insertRepos(repoList)
            userDao().insertUsers(userList)

    }

    suspend fun getGitHubRepo(owner: String): List<GitHubRepo> {
        return repoDao().getRepo(owner)
    }

     suspend fun saveStargazer(repos: List<Stargazer>) {

            val stargazerList = repos.map(::StargazerRepoTable)
            val userList = repos.map { it.user }.map(::LocalUser)
            stargazersDao().insertStargazers(stargazerList)
            userDao().insertUsers(userList)

    }

    suspend fun getStargazers(repoName: String): List<Stargazer> {
        return stargazersDao().readStargazersFromRepo(repoName)
    }

    suspend fun getAllRepos(): List<GitHubRepo> {
        return repoDao().getAllRepos()
    }
}

