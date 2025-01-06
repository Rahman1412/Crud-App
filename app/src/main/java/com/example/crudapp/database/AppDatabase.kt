package com.example.crudapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.crudapp.data.User

@Database(
    entities = [
        User::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase(){

    abstract fun UserDao(): UserDao
}