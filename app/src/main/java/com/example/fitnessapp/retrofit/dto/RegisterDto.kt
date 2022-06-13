package com.example.fitnessapp.retrofit.dto

import com.google.gson.annotations.SerializedName

data class RegisterDto(
    @SerializedName("token")
    val token: String,
    @SerializedName("user")
    val user: UserDto
)