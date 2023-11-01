package com.example.myapplication.retrofit

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MainApi {
    @GET("auth/user")
    suspend fun getUserInformation() : User

    @GET("auth/github")
    suspend fun initiateGithubAuthentication() : String

    @GET("auth/github/callback")
    suspend fun callbackAfterGithubAuthorization(code: String): AuthResponse

    @GET("auth/google")
    suspend fun initiateGoogleAuthentication() : String

    @GET("auth/google/callback")
    suspend fun callbackAfterGoogleAuthorization(code : String) : AuthResponse

    @POST("auth/signup")
    suspend fun userRegistration(@Body registrationData: UserDto) : Response<AuthResponse>

    @POST("auth/signin")
    suspend fun  userLogin(@Body loginData : UserAuthForm) : Response<AuthResponse>

    @GET("auth/refresh")
    suspend fun refreshAccessToken(refreshToken : String) : String
}