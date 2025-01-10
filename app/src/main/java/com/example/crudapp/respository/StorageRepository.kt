package com.example.crudapp.respository

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import com.example.crudapp.data.User
import com.example.crudapp.util.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StorageRepository @Inject constructor(
    private val dataStore: DataStore<UserPreferences>
) {

    val user: Flow<UserPreferences> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(UserPreferences.getDefaultInstance())
            } else {
                throw exception
            }
        }


    suspend fun updatedata(user:User){
        dataStore.updateData { preferences ->
            preferences.toBuilder()
                .setName(user.name)
                .setEmail(user.email)
                .build()
        }
    }

    suspend fun clearData(){
        dataStore.updateData {
            UserPreferences.getDefaultInstance()
        }
    }
}