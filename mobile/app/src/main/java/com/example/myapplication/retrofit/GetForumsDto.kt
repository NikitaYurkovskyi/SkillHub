package com.example.myapplication.retrofit

import java.util.Date

data class GetForumsDto(
    val id: String,
    val theme: String,
    val description: String,
    val createdAt: Date,
    val accessType: String
)
