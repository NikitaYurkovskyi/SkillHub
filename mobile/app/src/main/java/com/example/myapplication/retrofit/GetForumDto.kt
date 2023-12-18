package com.example.myapplication.retrofit

import java.util.Date


data class GetForumDto(
    val id: String,
    val theme: String,
    val description: String,
    val createdAt: String,
    val accessType: String,
    val memberships: List<Membership>,
    val messages: List<ForumMessageDto>
)

data class Membership(
    val id: String,
    val role: String,
    val connectedAt: String,
    val user: UserDto
)

data class UserDto(
    val id: String,
    val email: String,
    val name: String,
    val role: String,
    val birthDate: String,
    val userCreatedAt: String,
    val about: String,
    val nickname: String,
    val gender: String,
    val picture: String,
    val strategy: String
)
