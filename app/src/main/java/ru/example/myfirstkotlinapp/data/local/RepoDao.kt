package ru.example.myfirstkotlinapp.data.local

import androidx.room.*

@Dao
interface RepoDao {
    @Transaction
    @Query("SELECT * FROM repo_table WHERE ownerLogin = :owner ")
    suspend fun getRepo(owner: String): List<LocalGitHubRepo>


    @Query("SELECT * FROM repo_table ")
    suspend fun getAllRepos(): List<LocalGitHubRepo>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepo(repoTableModel: GitHubRepoTable)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepos(repoTableModels: List<GitHubRepoTable>)

}


