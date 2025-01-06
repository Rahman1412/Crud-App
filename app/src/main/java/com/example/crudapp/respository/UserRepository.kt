package com.example.crudapp.respository

import androidx.lifecycle.LiveData
import com.example.crudapp.data.User
import com.example.crudapp.database.UserDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userDao: UserDao
){
    val allUsers : LiveData<List<User>> = userDao.getAllUser();


    suspend fun insertUser(user:User){
        userDao.insertUser(user);
    }

    suspend fun updateUser(user:User){
        userDao.updateUser(user);
    }

    suspend fun deleteUser(user:User){
        userDao.deleteUser(user);
    }

}