package com.example.myapplication.retrofit

import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface MainApi {

    @POST("auth/sign-up")
    suspend fun userRegistration(@Body registrationData: SignUpDto) : Response<AuthResponse>

    @POST("auth/sign-in")
    suspend fun  userLogin(@Body loginData : UserAuthForm) : Response<AuthResponse>

    @Headers("Content-Type: application/json")
    @GET("user/profile")
    suspend fun getUserProfileInfo(@Header("Authorization") accessToken: String): Response<User>

    @Headers("Content-Type: application/json")
    @POST("auth/log-out")
    suspend fun logOut(@Header("Authorization") accessToken: String)

    @Headers("Content-Type: application/json")
    @PUT("user")
    suspend fun updateUser(
        @Header("Authorization") accessToken: String,
        @Body userInfo: SignUpDto
    ): Response<User>

    @GET("user/random/")
    suspend fun getRandomUserProfile(): List<OtherUserProfile>

    @Headers("Content-Type: application/json")
    @GET("chat")
    suspend fun getChats(
        @Header("Authorization") accessToken: String,
    ): List<ChatModel>

    @Headers("Content-Type: application/json")
    @GET("chat/{chatId}")
    suspend fun getChatById(
        @Header("Authorization") accessToken: String,
        @Path("chatId") chatId: String
    ): SingleChatModel

    @Headers("Content-Type: application/json")
    @POST("forum")
    suspend fun createForum (
        @Header("Authorization") accessToken: String,
        @Body forumDto: CreateForumDto
    ): Response <ForumDto>

    @Headers("Content-Type: application/json")
    @GET("forum")
    suspend fun getAllForums (
        @Header("Authorization") accessToken: String
    ): List<GetForumsDto>

    @Headers("Content-Type: application/json")
    @POST("/api/forum/{id}/join")
    suspend fun joinForum(
        @Header("Authorization") accessToken: String,
        @Path("id") id: String
    )

    @Headers("Content-Type: application/json")
    @GET("/api/forum/all-my-forums")
    suspend fun getAllMyForums(
        @Header("Authorization") accessToken: String
    ): List<GetAllMyForumsDto>

    @Headers("Content-Type: application/json")
    @GET("/api/forum/{id}")
    suspend fun getForumInfo(
        @Header("Authorization") accessToken: String,
        @Path("id") id: String
    ): Response<GetForumDto>

    @Headers("Content-Type: application/json")
    @POST("/api/forum/{id}/leave")
    suspend fun leaveForum (
        @Header("Authorization") accessToken: String,
        @Path("id") id: String
    )
}