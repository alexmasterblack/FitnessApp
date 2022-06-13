package com.example.fitnessapp.retrofit.dto

import com.google.gson.annotations.SerializedName

data class GenderDto(
    @SerializedName("code")
    val code: Int,
    @SerializedName("name")
    val name: String
)