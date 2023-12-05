package com.example.myapplication.retrofit

import java.util.Date

data class SingleChatModel(
    val id: String,
    val name: String?, // Nullable, так как в исходных данных "name" может быть null
    val createdAt: Date,
    val chatMembers: List<ChatMember>,
    val messages: List<Message>
)

data class ChatMember(
    val id: String,
    val joinedAt: Date,
    val user: UserData
)

data class UserData(
    val id: String,
    val email: String,
    val name: String,
    val about: String,
    val nickname: String,
    val gender: String
)

data class Message(
    val id: String,
    val text: String,
    val createdAt: Date,
    val user: UserData
)

