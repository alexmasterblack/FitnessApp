package com.example.fitnessapp.ui.login.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fitnessapp.retrofit.dto.RegisterDto
import com.example.fitnessapp.retrofit.network.FitnessService

class RegistrationViewModel : ViewModel() {

    private val fitnessService = FitnessService()

    private val _showLoginError = MutableLiveData<Boolean>(false)
    private val _showNameError = MutableLiveData<Boolean>(false)
    private val _showPasswordError = MutableLiveData<Boolean>(false)
    private val _showRepeatPasswordError = MutableLiveData<Boolean>(false)
    private val _showWrongRepeatPasswordError = MutableLiveData<Boolean>(false)
    private val _result = MutableLiveData<String>("")
    private val _token = MutableLiveData<String>("")

    val showLoginError: LiveData<Boolean> get() = _showLoginError
    val showNameError: LiveData<Boolean> get() = _showNameError
    val showPasswordError: LiveData<Boolean> get() = _showPasswordError
    val showRepeatPasswordError: LiveData<Boolean> get() = _showRepeatPasswordError
    val showWrongRepeatPasswordError: LiveData<Boolean> get() = _showWrongRepeatPasswordError
    val result: LiveData<String> get() = _result
    val token: LiveData<String> = _token

    fun onRegistrationClicked(
        login: String,
        password: String,
        passwordRepeat: String,
        name: String,
        gender: Int
    ) {
        if (login.isBlank()) {
            _showLoginError.postValue(true)
        }
        if (name.isBlank()) {
            _showNameError.postValue(true)
        }
        if (password.isBlank() || password.length < 8 ) {
            _showPasswordError.postValue(true)
        }
        if (passwordRepeat.isBlank()) {
            _showRepeatPasswordError.postValue(true)
        }
        if (passwordRepeat != password) {
            _showWrongRepeatPasswordError.postValue(true)
        }
        if (login.isNotBlank() && name.isNotBlank() && password.isNotBlank() && passwordRepeat.isNotBlank() && passwordRepeat == password) {
            fitnessService.register(
                login,
                password,
                name,
                gender,
                object : FitnessService.LoginCallback {
                    override fun onSuccess(result: RegisterDto) {
                        _token.value = result.token
                        _result.value = "Успех"
                    }

                    override fun onError(error: String) {
                        _result.value = error
                    }
                })
        }
    }
}