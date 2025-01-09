package com.example.crudapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.crudapp.data.Search
import com.example.crudapp.networks.ApiService
import com.example.crudapp.respository.OmdbRepository
import javax.inject.Inject

class MoviePagingSource @Inject constructor(
    private val query : String,
    private val omdbRepo: OmdbRepository

) : PagingSource<Int,Search>() {
    override fun getRefreshKey(state: PagingState<Int, Search>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Search> {

        return try{
            val currentPage = params.key ?: 1;
            val response = omdbRepo.searchMovie(query,currentPage,"fc1fef96");
            val data = response.body()?.Search ?: emptyList()
            LoadResult.Page(
                data = data,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (data.isEmpty()) null else currentPage + 1
            );
        }catch (e: Exception){
            LoadResult.Error(e);
        }

    }
}