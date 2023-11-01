package com.example.myapplication.retrofit

data class AuthResponse(
    val accessToken: String,
    val refreshToken: String,
    val userData: User
)
