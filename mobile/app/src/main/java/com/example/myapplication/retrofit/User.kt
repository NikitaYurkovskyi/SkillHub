package com.example.myapplication.retrofit

import java.util.Date

data class User(
    val providerName: String,
    val username: String,
    val email: String,
    val isAdmin: Boolean,
    val isBanned: Boolean,
    val gender: String,
    val regDate: Date,
    val version: Int,
    val id: String
)
