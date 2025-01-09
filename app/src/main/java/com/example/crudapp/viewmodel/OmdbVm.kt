package com.example.crudapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.crudapp.data.Search
import com.example.crudapp.respository.OmdbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OmdbVm @Inject constructor(
    private val omdbRepo: OmdbRepository
):ViewModel() {
    private val _movie = MutableLiveData<List<Search>>();
    private val _query = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class)
    val movies: Flow<PagingData<Search>> = _query.flatMapLatest { query ->
        omdbRepo.getMovie(query = query, page = 1, "fc1fef96")
    }.cachedIn(viewModelScope)

    fun setQuery(query: String) {
        _query.value = query
    }
}