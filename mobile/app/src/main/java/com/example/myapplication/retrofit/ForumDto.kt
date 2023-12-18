package com.example.myapplication.retrofit

import java.util.Date

data class ForumDto(
    val theme: String,
    val description: String,
    val id: String,
    val createdAt: Date,
    val accessType: String
)
