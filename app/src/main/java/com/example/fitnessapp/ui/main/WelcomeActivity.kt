package com.example.fitnessapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.fitnessapp.R
import com.example.fitnessapp.ui.login.login.LoginActivity
import com.example.fitnessapp.ui.login.registration.RegistrationActivity

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        findViewById<Button>(R.id.btnRegistration).setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
            finish()
        }

        findViewById<TextView>(R.id.loginView).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}