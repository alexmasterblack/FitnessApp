package com.example.fitnessapp.ui.login.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import com.example.fitnessapp.R
import com.example.fitnessapp.retrofit.AuthHolder
import com.example.fitnessapp.ui.CustomViewModelFactory
import com.example.fitnessapp.ui.main.MainAppActivity
import com.example.fitnessapp.ui.main.WelcomeActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    private val viewModel by viewModels<LoginViewModel> {
        CustomViewModelFactory { LoginViewModel(AuthHolder(applicationContext)) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<Toolbar>(R.id.toolbar).setNavigationOnClickListener {
            startActivity(Intent(this, WelcomeActivity::class.java))
            finish()
        }

        viewModel.showLoginError.observe(this) {
            if (it) {
                findViewById<TextInputLayout>(R.id.loginInput).error = "Введите логин"
            } else {
                findViewById<TextInputLayout>(R.id.loginInput).error = null
            }
        }
        viewModel.showPasswordError.observe(this) {
            if (it) {
                findViewById<TextInputLayout>(R.id.passwordInput).error = "Введите пароль"
            } else {
                findViewById<TextInputLayout>(R.id.passwordInput).error = null
            }
        }

        findViewById<Button>(R.id.btnLogin).setOnClickListener {
            viewModel.onLoginClicked(
                findViewById<TextInputEditText>(R.id.login).text.toString(),
                findViewById<TextInputEditText>(R.id.password).text.toString()
            )
        }

        viewModel.result.observe(this) {
            if (it == "Успех") {
                startActivity(Intent(this, MainAppActivity::class.java))
                finish()
            } else if (it != "") {
                Toast.makeText(
                    this,
                    it,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}