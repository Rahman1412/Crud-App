package com.example.crudapp

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.crudapp.databinding.ActivityPrefBinding
import com.example.crudapp.viewmodel.StorageVm
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PrefActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPrefBinding

    private val viewModel: StorageVm by viewModels()
    private lateinit var username : TextView
    private lateinit var useremail : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPrefBinding.inflate(layoutInflater)
        username = binding.username
        useremail = binding.useremail
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel.user.observe(this){ user ->
            Log.d("Something Updated","Local Storage Updated")
            username.text = user.name
            useremail.text = user.email
        }

        binding.logout.setOnClickListener{
            viewModel.clearStorage();
        }
    }
}