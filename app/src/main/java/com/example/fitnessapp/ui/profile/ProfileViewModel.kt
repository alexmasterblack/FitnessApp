package com.example.fitnessapp.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fitnessapp.retrofit.AuthHolder
import com.example.fitnessapp.retrofit.dto.RegisterDto
import com.example.fitnessapp.retrofit.dto.UserDto
import com.example.fitnessapp.retrofit.network.FitnessService
import okhttp3.Response

class ProfileViewModel(private val authHolder: AuthHolder) : ViewModel() {

    private val fitnessService = FitnessService()

    private val _login = MutableLiveData<String>("")
    private val _name = MutableLiveData<String>("")
    private val _result = MutableLiveData<String>("")

    val login: LiveData<String> get() = _login
    val name: LiveData<String> get() = _name
    val result: LiveData<String> get() = _result

    fun setProfile() {
        val token = "Bearer ".plus(authHolder.getToken())
        fitnessService.profile(token, object : FitnessService.ProfileCallback {
            override fun onSuccess(result: UserDto) {
                _login.value = result.login
                _name.value = result.name
            }

            override fun onError(error: String) {
                _login.value = ""
                _name.value = ""
            }

        })
    }

    fun onLogoutClicked() {
        val token = "Bearer ".plus(authHolder.getToken())
        fitnessService.logout(token, object : FitnessService.LogoutCallback {
            override fun onSuccess(result: String) {
                authHolder.cleanToken()
                _result.value = "Успех"
            }

            override fun onError(error: String) {
                _result.value = error
            }
        })
    }
}