package com.example.crudapp.di

import android.content.Context
import androidx.room.Room
import com.example.crudapp.database.AppDatabase
import com.example.crudapp.database.UserDao
import com.example.crudapp.networks.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
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
    @Singleton
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create()).build();
    }

    @Provides
    @Singleton
    fun provieApiService(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java);
    }

    @Provides
    fun provideUserDao(database: AppDatabase):UserDao{
        return database.UserDao();
    }

}