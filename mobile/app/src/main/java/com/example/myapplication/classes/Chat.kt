package com.example.myapplication.classes

import android.util.Log
import com.example.myapplication.retrofit.ChatModel
import com.example.myapplication.retrofit.Message
import com.example.myapplication.retrofit.RetrofitInit
import com.example.myapplication.retrofit.SingleChatModel
import io.socket.client.Socket
import org.json.JSONObject

class Chat {

    suspend fun getAllUserChat(
        retrofitInit: RetrofitInit,
        baseURL: String,
        tokenManager: TokenManager
    ): List<ChatModel>?{
        retrofitInit.initRetrofit(baseURL)
        try {
            val tokensPair = tokenManager.retrieveTokens()
            val accessToken = "Bearer " + tokensPair.first
            val refreshToken = "Bearer " + tokensPair.second
            if (accessToken.isEmpty() || refreshToken.isEmpty()) {
                return null
            }
            return retrofitInit.mainApi.getChats(accessToken)
        } catch (e: Exception) {
            Log.e("Error while getting chats", e.toString())
            return null
        }
    }

    suspend fun getChatById(
        retrofitInit: RetrofitInit,
        baseURL: String,
        tokenManager: TokenManager,
        chatId: String
    ): SingleChatModel?{
        retrofitInit.initRetrofit(baseURL)
        try {
            val tokenPair = tokenManager.retrieveTokens()
            val accessToken = "Bearer " + tokenPair.first
            val refreshToken = "Bearer " + tokenPair.second
            if (accessToken.isEmpty() || refreshToken.isEmpty()) {
                return null
            }

            return retrofitInit.mainApi.getChatById(accessToken, chatId)
        } catch (e: Exception) {
            Log.e("Error while getting chat", e.toString())
            return null
        }
    }


    suspend fun getMessageOfChatById(
        retrofitInit: RetrofitInit,
        baseURL: String,
        tokenManager: TokenManager,
        chatId: String
    ): List<Message>?{
        retrofitInit.initRetrofit(baseURL)
        try {
            val tokenPair = tokenManager.retrieveTokens()
            val accessToken = "Bearer " + tokenPair.first
            val refreshToken = "Bearer " + tokenPair.second
            if (accessToken.isEmpty() || refreshToken.isEmpty()) {
                return null
            }

            return retrofitInit.mainApi.getChatById(accessToken, chatId).messages
        } catch (e: Exception) {
            Log.e("Error while getting chat", e.toString())
            return null
        }
    }
}