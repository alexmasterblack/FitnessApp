package com.example.fitnessapp.retrofit.network

import android.util.Log
import com.example.fitnessapp.retrofit.api.FitnessApi
import com.example.fitnessapp.retrofit.dto.RegisterDto
import com.example.fitnessapp.retrofit.dto.UserDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class FitnessService {

    private val fitnessApi = NetworkService().retrofit.create(FitnessApi::class.java)

    fun register(
        login: String,
        password: String,
        name: String,
        gender: Int,
        callback: LoginCallback
    ) {
        fitnessApi.register(login, password, name, gender)
            .enqueue(object : Callback<RegisterDto> {
                override fun onResponse(call: Call<RegisterDto>, response: Response<RegisterDto>) {
                    if (response.isSuccessful) {
                        response.body()?.let { callback.onSuccess(it) } ?: callback.onError(
                            IOException("Server returned error").toString()
                        )
                    } else callback.onError(response.message())
                }

                override fun onFailure(call: Call<RegisterDto>, t: Throwable) {
                    callback.onError(t.toString())
                }
            })
    }

    fun login(
        login: String,
        password: String,
        callback: LoginCallback
    ) {
        fitnessApi.login(login, password).enqueue(object : Callback<RegisterDto> {
            override fun onResponse(call: Call<RegisterDto>, response: Response<RegisterDto>) {
                if (response.isSuccessful) {
                    response.body()?.let { callback.onSuccess(it) }
                        ?: callback.onError(IOException("Server returned error").toString())

                } else callback.onError(response.message())

            }

            override fun onFailure(call: Call<RegisterDto>, t: Throwable) {
                callback.onError(t.toString())
            }
        })
    }

    fun logout(token: String, callback: LogoutCallback) {
        fitnessApi.logout(token).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.code() == 200) {
                    if (response.message() == "OK") {
                        callback.onSuccess(response.message())
                    } else {
                        callback.onError(IOException("Server returned error").toString())
                    }
                } else callback.onError(response.message())
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                callback.onError(t.toString())
            }

        })
    }

    fun profile(token: String, callback: ProfileCallback) {
        fitnessApi.profile(token).enqueue(object : Callback<UserDto> {
            override fun onResponse(call: Call<UserDto>, response: Response<UserDto>) {
                if (response.isSuccessful) {
                    response.body()?.let { callback.onSuccess(it) }
                        ?: callback.onError(IOException("Server returned error").toString())

                } else callback.onError(response.message())
            }

            override fun onFailure(call: Call<UserDto>, t: Throwable) {
                callback.onError(t.toString())
            }

        })
    }

    interface LoginCallback {
        fun onSuccess(result: RegisterDto)
        fun onError(error: String)
    }

    interface LogoutCallback {
        fun onSuccess(result: String)
        fun onError(error: String)
    }

    interface ProfileCallback {
        fun onSuccess(result: UserDto)
        fun onError(error: String)
    }
}