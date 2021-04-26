package ru.example.myfirstkotlinapp.model

import java.util.*

interface Stargazer {

    val user: User
    val starred_at: Date
    val repoName: String

}
