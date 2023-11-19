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
}