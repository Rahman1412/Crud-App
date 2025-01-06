package com.example.crudapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crudapp.adapter.UserAdapter
import com.example.crudapp.data.User
import com.example.crudapp.databinding.ActivityMainBinding
import com.example.crudapp.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var userVm : UserViewModel
    private lateinit var adapter: UserAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root);

        val toolbar: androidx.appcompat.widget.Toolbar = binding.customToolbar;
        setSupportActionBar(toolbar);

        val btnToolbar : Button = binding.buttonInToolbar;
        btnToolbar.setOnClickListener {
            startActivity(Intent(this,UserActivity::class.java));
        }

        userVm = ViewModelProvider(this)[UserViewModel::class.java];

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val rvUsers: RecyclerView = binding.rvUsers

        adapter = UserAdapter { user -> userVm.deleteUser(user) }
        rvUsers.layoutManager = LinearLayoutManager(this)
        rvUsers.adapter = adapter

        userVm.allUsers.observe(this) { users ->
            adapter.setUsers(users)
        }
    }
}