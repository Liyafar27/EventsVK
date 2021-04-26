package ru.example.myfirstkotlinapp.data.local

import androidx.room.Embedded
import androidx.room.Relation
import ru.example.myfirstkotlinapp.model.GitHubRepo
import java.util.*

class LocalGitHubRepo : GitHubRepo {

    @Embedded
    lateinit var repoTable: GitHubRepoTable

    @Relation(parentColumn = "ownerLogin", entityColumn = "login")
    override lateinit var owner: LocalUser

    override val id: Int
        get() = repoTable.id

    override val name: String
        get() = repoTable.name

    override val stargazers_count: String
        get() = repoTable.stargazers_count

    override val created_at: Date
        get() = repoTable.created_at

}





