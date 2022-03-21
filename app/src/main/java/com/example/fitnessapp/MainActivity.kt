package com.example.fitnessapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fitnessapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegistration.setOnClickListener {
            startActivity(Intent(this, Registration::class.java))
            finish()
        }

        binding.loginView.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
            finish()
        }
    }
}