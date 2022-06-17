package com.example.fitnessapp.retrofit.api

import com.example.fitnessapp.retrofit.dto.RegisterDto
import com.example.fitnessapp.retrofit.dto.UserDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface FitnessApi {

    @POST("/api/auth/register")
    fun register(
        @Query("login") login: String,
        @Query("password") password: String,
        @Query("name") name: String,
        @Query("gender") gender: Int
    ): Call<RegisterDto>

    @POST("/api/auth/login")
    fun login(
        @Query("login") login: String,
        @Query("password") password: String
    ): Call<RegisterDto>

    @POST("/api/auth/logout")
    fun logout(
        @Header("Authorization") authorization: String
    ): Call<Void>

    @GET("/api/user/profile")
    fun profile(
        @Header("Authorization") authorization: String
    ): Call<UserDto>
}