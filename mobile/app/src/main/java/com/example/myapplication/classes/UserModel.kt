package com.example.myapplication.classes

import android.util.Log
import com.example.myapplication.retrofit.MainApi
import com.example.myapplication.retrofit.OtherUserProfile
import com.example.myapplication.retrofit.RetrofitInit
import com.example.myapplication.retrofit.SignUpDto
import com.example.myapplication.retrofit.User
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.Locale

class UserModel {

    suspend fun isUserAuth(
        tokenManager: TokenManager,
        mainApi: MainApi,
        retrofitInit: RetrofitInit,
        baseURL: String
    ): Boolean {
        val tokensPair = tokenManager.retrieveTokens()
        val accessToken = "Bearer " + tokensPair.first
        val refreshToken = "Bearer " + tokensPair.second
        if (accessToken.isNullOrEmpty() || refreshToken.isNullOrEmpty()) {
            return false
        }
        retrofitInit.initRetrofit(baseURL)
        try {
            val response = accessToken?.let { retrofitInit.mainApi.getUserProfileInfo(it) }
            if (response?.code() == 404) {
                return false
            }
            if (response?.code() == 401){
                return false
            }
        } catch (e: Exception) {
            return false
        }
        return true
    }

    suspend fun logOut(
        tokenManager: TokenManager,
        retrofitInit: RetrofitInit,
        baseURL: String
    ) {
        retrofitInit.initRetrofit(baseURL)
        val tokensPair = tokenManager.retrieveTokens()
        val accessToken = "Bearer " + tokensPair.first
        val refreshToken = "Bearer " + tokensPair.second
        if (accessToken.isNullOrEmpty() || refreshToken.isNullOrEmpty()) {
            Log.e("Log out exception", "Invalid tokens")
            return
        }
        try {
            retrofitInit.mainApi.logOut(accessToken)
            runBlocking {
                tokenManager.clearTokens()
            }
        } catch (e: Exception) {
            Log.e("Log out exception", e.toString())
        }
    }



    suspend fun getUserInfo(
        tokenManager: TokenManager,
        retrofitInit: RetrofitInit,
        baseURL: String
    ): User? {
        val tokensPair = tokenManager.retrieveTokens()
        val accessToken = "Bearer " + tokensPair.first
        val refreshToken = tokensPair.second
        if (accessToken.isNullOrEmpty() || refreshToken.isNullOrEmpty()) {
            return null
        }
        retrofitInit.initRetrofit(baseURL)
        try {
            val response = retrofitInit.mainApi.getUserProfileInfo(accessToken)
            if (response?.code() == 404) {
                return null
            }
            if (response?.code() == 200){
                return response.body()
            }
        } catch (e: Exception) {
            return null
        }
        return null
    }

    suspend fun updateUser(
        newEmail: String,
        newName: String,
        newNickname: String,
        newBirthDate: String,
        tokenManager: TokenManager,
        retrofitInit: RetrofitInit,
        baseURL: String
    ): User? {
        return try {
            val tokensPair = tokenManager.retrieveTokens()
            val accessToken = "Bearer " + tokensPair.first
            val refreshToken = "Bearer " + tokensPair.second
            if (accessToken.isNullOrEmpty() || refreshToken.isNullOrEmpty()) {
                return null
            }

            retrofitInit.initRetrofit(baseURL)

            val password = "1qazxsw2"
            val dateFormatInput = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
            val date = dateFormatInput.parse(newBirthDate)

            val dateFormatOutput = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val formattedDate = dateFormatOutput.format(date)

            val userInfo = SignUpDto(
                newEmail,
                password,
                newName,
                formattedDate,
                newNickname
            )

            val response = retrofitInit.mainApi.updateUser(accessToken, userInfo)

            if (response.code() == 200) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            Log.e("Update User Exception", e.toString())
            null
        }
    }


    suspend fun getAllUser(
        retrofitInit: RetrofitInit,
        baseURL: String,
    ): List<OtherUserProfile>? {
        retrofitInit.initRetrofit(baseURL)
        try {
            val userProfiles = retrofitInit.mainApi.getRandomUserProfile()
            return userProfiles
        } catch (e: Exception) {
            Log.e("Error while getting users", e.toString())
            null
        }
        return null
    }


}