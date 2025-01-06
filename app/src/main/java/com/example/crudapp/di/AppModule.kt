package com.example.crudapp.di

import android.content.Context
import androidx.room.Room
import com.example.crudapp.database.AppDatabase
import com.example.crudapp.database.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context): AppDatabase{
        return Room.databaseBuilder(context,AppDatabase::class.java,"user_crud").build();
    }

    @Provides
    fun provideUserDao(database: AppDatabase):UserDao{
        return database.UserDao();
    }

}