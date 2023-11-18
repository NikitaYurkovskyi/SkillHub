package com.example.myapplication.retrofit

import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

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

    @POST("auth/sign-up")
    suspend fun userRegistration(@Body registrationData: SignUpDto) : Response<AuthResponse>

    @POST("auth/sign-in")
    suspend fun  userLogin(@Body loginData : UserAuthForm) : Response<AuthResponse>

    @GET("auth/refresh")
    suspend fun refreshAccessToken(refreshToken : String) : String
}