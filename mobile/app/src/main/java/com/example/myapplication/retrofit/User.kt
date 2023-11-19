package com.example.myapplication.retrofit

import java.util.Date

data class User(
    val id: String,
    val email: String,
    val name: String,
    val role: String,
    val birthDate: Date,
    val createdAt: Date,
    val about: String,
    val nickname: String,
    val gender: String,
    val picture: String
)
