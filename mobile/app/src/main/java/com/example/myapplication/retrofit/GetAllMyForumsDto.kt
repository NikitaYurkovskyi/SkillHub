package com.example.myapplication.retrofit
data class GetAllMyForumsDto(
    val id: String,
    val theme: String,
    val description: String,
    val createdAt: String,
    val accessType: String,
    val membership: List<MembershipDto>
)

data class MembershipDto(
    val id: String,
    val role: String,
    val connectedAt: String
)
