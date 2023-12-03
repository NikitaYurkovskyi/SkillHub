package com.example.myapplication.retrofit

import java.util.Date

data class ChatModel(
    val chat: ChatDetail,
    val members: List<User>,
)

data class ChatDetail(
    val id: String,
    val name: String,
    val createdAt: Date
)
