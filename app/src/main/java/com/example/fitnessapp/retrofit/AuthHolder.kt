package com.example.fitnessapp.retrofit

import android.content.Context
import android.content.SharedPreferences

class AuthHolder(
    private val context: Context
) {

    companion object {
        const val PREFS_AUTH = "prefs"
    }

    private val sharedPreferences = context.getSharedPreferences(PREFS_AUTH, Context.MODE_PRIVATE)

    fun getToken(): String {
        return sharedPreferences.getString(PREFS_AUTH, "")!!
    }

    fun cleanToken() {
        sharedPreferences.edit().remove(PREFS_AUTH).apply()
    }

    fun saveToken(token: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(PREFS_AUTH, token)
        editor.apply()
    }
}