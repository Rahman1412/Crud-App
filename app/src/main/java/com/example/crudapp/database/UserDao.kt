package com.example.crudapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.crudapp.data.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user:User)

    @Update
    suspend fun updateUser(user:User)

    @Delete
    suspend fun deleteUser(user:User)

    @Query("SELECT * FROM `user_tbl` ORDER BY `id` DESC")
    fun getAllUser() : LiveData<List<User>>
}