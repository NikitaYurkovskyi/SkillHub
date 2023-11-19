package com.example.myapplication.classes

import android.content.Context

class TokenManager(val context: Context) {
    fun saveTokens(accessToken: String, refreshToken: String) {
        val sharedPreferences = context.getSharedPreferences("TOKENS", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("ACCESS_TOKEN", accessToken)
        editor.putString("REFRESH_TOKEN", refreshToken)
        editor.apply()
    }

    fun retrieveTokens(): Pair<String?, String?> {
        val sharedPreferences = context.getSharedPreferences("TOKENS", Context.MODE_PRIVATE)
        val accessToken = sharedPreferences.getString("ACCESS_TOKEN", null)
        val refreshToken = sharedPreferences.getString("REFRESH_TOKEN", null)
        return Pair(accessToken, refreshToken)
    }

    fun clearTokens() {
        val sharedPreferences = context.getSharedPreferences("TOKENS", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("ACCESS_TOKEN")
        editor.remove("REFRESH_TOKEN")
        editor.commit()
    }

}