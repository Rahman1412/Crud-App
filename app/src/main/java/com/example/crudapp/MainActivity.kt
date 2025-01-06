package com.example.crudapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
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

        userVm = ViewModelProvider(this)[UserViewModel::class.java];

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etName: EditText = binding.etName
        val etEmail: EditText = binding.etEmail
        val rvUsers: RecyclerView = binding.rvUsers

        binding.addUser.setOnClickListener {
            userVm.insertUser(User(name = "Pasha", email = "pasha@gmail.com"));
        }

        adapter = UserAdapter { user -> userVm.deleteUser(user) }
        rvUsers.layoutManager = LinearLayoutManager(this)
        rvUsers.adapter = adapter

        userVm.allUsers.observe(this) { users ->
            adapter.setUsers(users)
        }
    }
}