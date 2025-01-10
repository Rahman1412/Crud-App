package com.example.crudapp.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crudapp.data.User
import com.example.crudapp.respository.UserRepository
import com.example.crudapp.MyForegroundService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepo : UserRepository
) : ViewModel() {
    val allUsers : LiveData<List<User>> = userRepo.allUsers;

    fun insertUser(user:User){
        viewModelScope.launch{
            userRepo.insertUser(user);
        }
    }

    fun updateUser(user:User){
        viewModelScope.launch {
            userRepo.updateUser(user);
        }
    }

    fun deleteUser(user:User){
        viewModelScope.launch {
            userRepo.deleteUser(user);
        }
    }

    fun startService(context: Context) {
        val intent = Intent(context, MyForegroundService::class.java)
        context.startService(intent)
    }

    fun stopService(context: Context) {
        val intent = Intent(context, MyForegroundService::class.java)
        context.stopService(intent)
    }
}