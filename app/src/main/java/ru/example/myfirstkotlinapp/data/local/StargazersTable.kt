package ru.example.myfirstkotlinapp.data.local

import java.util.*
import androidx.room.*
import ru.example.myfirstkotlinapp.model.Stargazer
import ru.example.myfirstkotlinapp.utils.DateConverter

@Entity(tableName = "stargazers_table")
@TypeConverters(DateConverter::class)
data class StargazerRepoTable(
    @PrimaryKey
    val stargazerLogin: String,
    val starred_at: Date,
    val repoName: String

) {
    constructor(
        repo: Stargazer
    ) : this(
        repo.user.login.toLowerCase(Locale.ENGLISH),
        repo.starred_at,
        repo.repoName
    )
}


