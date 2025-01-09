package com.example.crudapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.crudapp.data.User
import com.example.crudapp.databinding.ActivityUserBinding
import com.example.crudapp.viewmodel.OmdbVm
import com.example.crudapp.viewmodel.UserViewModel
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding
    private lateinit var name: TextInputEditText;
    private lateinit var email: TextInputEditText;
    private lateinit var userVm: UserViewModel;
    private var id: Int? = null;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root);
        userVm = ViewModelProvider(this)[UserViewModel::class.java];
        id = intent.getIntExtra("id",0);
        if(id!! > 0){
            binding.addUser.setText("Update User");
        }
        name = binding.name;
        email = binding.email;
        name.setText(intent.getStringExtra("name"));
        email.setText(intent.getStringExtra("email"));

        binding.addUser.setOnClickListener {
            userVm.insertUser(User(id = id!!.toInt() ,name = name.text.toString(),email = email.text.toString()));
            name.text?.clear();
            email.text?.clear();
            Toast.makeText(this,"User added successfully!",Toast.LENGTH_SHORT).show();
            finish();
        }


        binding.searchMovie.setOnClickListener {
            startActivity(Intent(this,MovieActivity::class.java));
        }
    }
}