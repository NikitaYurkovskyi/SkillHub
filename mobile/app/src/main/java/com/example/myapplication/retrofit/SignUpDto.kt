package com.example.myapplication.retrofit

import java.util.Date

data class SignUpDto(
    val email: String,
    val password: String,
    val name: String,
    val birthDate: String,
    val nickname: String,
)
