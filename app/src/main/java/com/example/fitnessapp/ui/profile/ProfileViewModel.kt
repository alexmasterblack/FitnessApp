package com.example.fitnessapp.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fitnessapp.retrofit.dto.RegisterDto
import com.example.fitnessapp.retrofit.dto.UserDto
import com.example.fitnessapp.retrofit.network.FitnessService
import okhttp3.Response

class ProfileViewModel : ViewModel() {

    private val fitnessService = FitnessService()

    private val _login = MutableLiveData<String>("")
    private val _name = MutableLiveData<String>("")
    private val _result = MutableLiveData<String>("")

    val login: LiveData<String> get() = _login
    val name: LiveData<String> get() = _name
    val result: LiveData<String> get() = _result

    fun setProfile(token: String) {
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

    fun onLogoutClicked(token: String) {
        fitnessService.logout(token, object : FitnessService.LogoutCallback {
            override fun onSuccess(result: String) {
                _result.value = "Успех"
            }

            override fun onError(error: String) {
                _result.value = error
            }
        })
    }
}