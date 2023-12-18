package com.example.myapplication.retrofit

import java.util.Date

data class ForumMessageDto (
    val id: String,
    val text: String,
    val createdAt: Date,
    val forumId: String,
    val user: MessageSenderDto
)

data class MessageSenderDto (
    val id: String
)