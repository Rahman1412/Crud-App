package com.example.crudapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.crudapp.data.User
import com.example.crudapp.respository.StorageRepository
import com.example.crudapp.util.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StorageVm @Inject constructor(
    private val storageRepository: StorageRepository
): ViewModel() {

    val user : LiveData<UserPreferences> = storageRepository.user.asLiveData();

    fun updateStorage(user:User){
        viewModelScope.launch {
            storageRepository.updatedata(user)
        }
    }

    fun clearStorage(){
        viewModelScope.launch{
            storageRepository.clearData()
        }
    }

}