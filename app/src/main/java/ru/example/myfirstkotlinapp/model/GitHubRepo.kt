package ru.example.myfirstkotlinapp.model
import java.util.*


interface GitHubRepo {

    val id: Int
    val name: String
    val stargazers_count: String
    val owner: User
    val created_at: Date
}

