package com.example.crudapp.respository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.crudapp.data.ApiResponse
import com.example.crudapp.data.Search
import com.example.crudapp.networks.ApiService
import com.example.crudapp.paging.MoviePagingSource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OmdbRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun searchMovie(query:String?,page:Int?,apiKey:String?):Response<ApiResponse>{
        return apiService.searchMovie(query,page,apiKey);
    }

    fun getMovie(query: String,page: Int?,apiKey: String?): Flow<PagingData<Search>>{
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviePagingSource(query,this) }
        ).flow
    }
}