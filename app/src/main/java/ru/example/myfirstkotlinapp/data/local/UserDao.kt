package ru.example.myfirstkotlinapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(stargazerTableModel: LocalUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(stargazersTableModels: List<LocalUser>)

}