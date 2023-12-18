package com.example.myapplication.classes

import android.util.Log
import com.example.myapplication.retrofit.CreateForumDto
import com.example.myapplication.retrofit.ForumDto
import com.example.myapplication.retrofit.GetAllMyForumsDto
import com.example.myapplication.retrofit.GetForumDto
import com.example.myapplication.retrofit.GetForumsDto
import com.example.myapplication.retrofit.RetrofitInit
import retrofit2.Response

class Forum{

    suspend fun createForum(
        theme: String,
        description: String,
        tokenManager: TokenManager,
        retrofitInit: RetrofitInit,
        baseURL: String
    ): Response<ForumDto>? {

        Log.e("Forum", "Here")

        val createForumDto = CreateForumDto(theme, description)
        retrofitInit.initRetrofit(baseURL)
        try {
            val tokensPair = tokenManager.retrieveTokens()
            val accessToken = "Bearer " + tokensPair.first
            val refreshToken = "Bearer " + tokensPair.second
            if (accessToken.isEmpty() || refreshToken.isEmpty()) {
                return null
            }

            val response = retrofitInit.mainApi.createForum(accessToken, createForumDto);

            return response;
        } catch (e: Exception) {
            Log.e("Forum", e.toString())
            e.printStackTrace()
        }

        return null
    }

    suspend fun getAllForums(
        tokenManager: TokenManager,
        retrofitInit: RetrofitInit,
        baseURL: String
    ): List<GetForumsDto>? {
        retrofitInit.initRetrofit(baseURL)
        try {
            val tokensPair = tokenManager.retrieveTokens()
            val accessToken = "Bearer " + tokensPair.first
            val refreshToken = "Bearer " + tokensPair.second
            if (accessToken.isEmpty() || refreshToken.isEmpty()) {
                return null
            }
            val response = retrofitInit.mainApi.getAllForums(accessToken);
            return response;
        } catch (e: Exception) {
            Log.e("Forum", e.toString())
            e.printStackTrace()
        }

        return null
    }

    suspend fun joinForum(
        tokenManager: TokenManager,
        retrofitInit: RetrofitInit,
        baseURL: String,
        forumId: String
    ) {
        retrofitInit.initRetrofit(baseURL)
        try {
            val tokensPair = tokenManager.retrieveTokens()
            val accessToken = "Bearer " + tokensPair.first
            val refreshToken = "Bearer " + tokensPair.second
            if (accessToken.isNotEmpty() || refreshToken.isNotEmpty()) {
                retrofitInit.mainApi.joinForum(accessToken, forumId)
            }
        } catch (e: Exception) {
            Log.e("Forum", e.toString())
            e.printStackTrace()
        }

    }

    suspend fun getAllMyForums(
        tokenManager: TokenManager,
        retrofitInit: RetrofitInit,
        baseURL: String
    ): List<GetAllMyForumsDto>? {
        retrofitInit.initRetrofit(baseURL)

        try {
            val tokensPair = tokenManager.retrieveTokens()
            val accessToken = "Bearer " + tokensPair.first
            val refreshToken = "Bearer " + tokensPair.second
            if (accessToken.isEmpty() || refreshToken.isEmpty()) {
                return null
            }
            val allMyForumsDto = retrofitInit.mainApi.getAllMyForums(accessToken);
            return allMyForumsDto
        } catch (e: Exception) {
            Log.e("Forum", e.toString())
            e.printStackTrace()
        }

        return null
    }

    suspend fun getForumInfo (
        tokenManager: TokenManager,
        retrofitInit: RetrofitInit,
        baseURL: String,
        forumId: String
    ): GetForumDto? {
        retrofitInit.initRetrofit(baseURL)

        try {
            val tokensPair = tokenManager.retrieveTokens()
            val accessToken = "Bearer " + tokensPair.first
            val refreshToken = "Bearer " + tokensPair.second
            if (accessToken.isEmpty() || refreshToken.isEmpty()) {
                return null
            }
            val getForumDto = retrofitInit.mainApi.getForumInfo(accessToken, forumId);
            return getForumDto.body()
        } catch (e: Exception) {
            Log.e("Forum", e.toString())
            e.printStackTrace()
        }

        return null
    }
}