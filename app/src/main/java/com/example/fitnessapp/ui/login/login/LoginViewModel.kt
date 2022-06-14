package com.example.fitnessapp.ui.login.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fitnessapp.retrofit.AuthHolder
import com.example.fitnessapp.retrofit.dto.RegisterDto
import com.example.fitnessapp.retrofit.network.FitnessService

class LoginViewModel(private val authHolder: AuthHolder) : ViewModel() {

    private val fitnessService = FitnessService()

    private val _showLoginError = MutableLiveData<Boolean>(false)
    private val _showPasswordError = MutableLiveData<Boolean>(false)
    private val _result = MutableLiveData<String>("")

    val showLoginError: LiveData<Boolean> get() = _showLoginError
    val showPasswordError: LiveData<Boolean> get() = _showPasswordError
    val result: LiveData<String> get() = _result

    fun onLoginClicked(login: String, password: String) {
        if (login.isBlank()) {
            _showLoginError.postValue(true)
        }

        if (password.isBlank()) {
            _showPasswordError.postValue(true)
        }

        if (login.isNotBlank() && password.isNotBlank()) {
            fitnessService.login(login, password, object : FitnessService.LoginCallback {
                override fun onSuccess(result: RegisterDto) {
                    authHolder.saveToken(result.token)
                    _result.value = "Успех"
                }

                override fun onError(error: String) {
                    _result.value = error
                }
            })
        }
    }
}