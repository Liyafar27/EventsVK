package ru.example.myfirstkotlinapp.data.local

import androidx.room.*

@Dao

interface StargazersDao {
    @Transaction

    @Query("SELECT * FROM stargazers_table WHERE repoName = :rep")
    suspend fun readStargazersFromRepo(rep: String): List<LocalStargazer>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStargazer(stargazerTableModel: StargazerRepoTable)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStargazers(stargazersTableModels: List<StargazerRepoTable>)
}
