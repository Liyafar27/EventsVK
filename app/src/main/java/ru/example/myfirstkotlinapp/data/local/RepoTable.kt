package ru.example.myfirstkotlinapp.data.local

import ru.example.myfirstkotlinapp.model.GitHubRepo
import java.util.*
import androidx.room.*
import ru.example.myfirstkotlinapp.utils.DateConverter

@Entity(tableName = "repo_table")
@TypeConverters(DateConverter::class)
data class GitHubRepoTable(
    @PrimaryKey
    val id: Int,
    val name: String,
    val stargazers_count: String,
    val ownerLogin: String,
    val created_at: Date
) {
    constructor(repo: GitHubRepo) : this(
        repo.id,
        repo.name,
        repo.stargazers_count,
        repo.owner.login.toLowerCase(Locale.ENGLISH),
        repo.created_at
    )
}



