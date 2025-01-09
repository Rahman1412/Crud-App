package com.example.crudapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crudapp.adapter.MovieAdapter
import com.example.crudapp.databinding.ActivityMainBinding
import com.example.crudapp.databinding.ActivityMovieBinding
import com.example.crudapp.viewmodel.OmdbVm
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMovieBinding;
    private lateinit var omdbVm : OmdbVm;
    private lateinit var adapter : MovieAdapter;
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMovieBinding.inflate(layoutInflater);
        setContentView(binding.root);
        omdbVm = ViewModelProvider(this)[OmdbVm::class.java];
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        adapter = MovieAdapter();
        binding.rvMovie.layoutManager = LinearLayoutManager(this);
        binding.rvMovie.adapter = adapter;


        lifecycleScope.launch {
            omdbVm.movies.collectLatest{
                adapter.submitData(it);
            }
        }

        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { loadStates ->
                when {
                    loadStates.refresh is LoadState.Loading -> binding.progressBar.visibility = View.VISIBLE
                    loadStates.append is LoadState.Loading -> binding.rvMovie.visibility = View.VISIBLE
                    loadStates.refresh is LoadState.NotLoading && loadStates.append is LoadState.NotLoading -> binding.progressBar.visibility = View.GONE
                    loadStates.refresh is LoadState.Error || loadStates.append is LoadState.Error -> binding.progressBar.visibility = View.GONE
                }
            }
        }

        searchView = binding.searchView;

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    omdbVm.setQuery(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    omdbVm.setQuery(newText);
                }
                return true
            }
        })

    }
}