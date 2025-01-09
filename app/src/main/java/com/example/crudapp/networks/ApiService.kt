package com.example.crudapp.networks

import com.example.crudapp.data.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/")
    suspend fun searchMovie(
        @Query("s") query: String? = null,
        @Query("page") page: Int? = null,
        @Query("apikey") apiKey: String? = null
    ) : Response<ApiResponse>
}