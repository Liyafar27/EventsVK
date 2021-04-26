package ru.example.myfirstkotlinapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.example.myfirstkotlinapp.model.User
import java.util.*


@Entity(tableName = "user_table")
data class LocalUser(
    @PrimaryKey

    override val login: String,
    override val avatar_url: String
) : User {
    constructor(user: User) : this(
        user.login.trim().toLowerCase(Locale.US), user.avatar_url
    )
}
