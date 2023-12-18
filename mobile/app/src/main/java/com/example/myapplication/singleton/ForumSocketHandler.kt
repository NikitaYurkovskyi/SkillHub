package com.example.myapplication.singleton

import android.util.Log
import com.example.myapplication.BuildConfig
import io.socket.client.IO
import io.socket.client.Socket
import org.json.JSONObject
import java.net.URISyntaxException
import java.util.Collections

object ForumSocketHandler {
    private var mSocket: Socket? = null

    @Synchronized
    fun setForumSocket(accessToken: String){
        try {
            Log.i("Socket", "Access Token: $accessToken")
            var options = IO.Options
                .builder()
                .setExtraHeaders(
                    Collections.singletonMap(
                        "authorization",
                        Collections.singletonList("Bearer $accessToken")
                    )
                )
                .build()

            mSocket = IO.socket(BuildConfig.FORUM_SOCKET_URL, options)

            mSocket?.on(Socket.EVENT_CONNECT) {
                val authorization = "Bearer $accessToken"
                Log.i("Socket", "Forum socket has been connected")

            }
            //mSocket?.on("newMessage", onNewMessage()) // Fix: Use onNewMessage() as the callback
            mSocket?.on(Socket.EVENT_CONNECT_ERROR) { args ->
                val exception = args[0] as Exception
                Log.e("Socket", "Connection error: ${exception.message}")
            }

        } catch (e: URISyntaxException) {
            Log.e("Socket", e.toString())
        } catch (e: Exception) {
            Log.e("Socket", "Exception: ${e.message}")
        }
    }

    @Synchronized
    fun establishConnection(){
        mSocket?.connect()
    }

    @Synchronized
    fun closeConnection(){
        mSocket?.disconnect()
    }

    @Synchronized
    fun getSocket(): Socket?{
        return mSocket
    }


    @Synchronized
    fun sendForumMessage(messageText: String, forumId: String) {
        try {
            val jsonObject = JSONObject().put("text", messageText)
            jsonObject.put("forumId", forumId)

            mSocket?.emit("newMessage", jsonObject)
            Log.i("Socket", "Message was send")
        } catch (e: Error) {
            Log.e("Forum", e.toString())
        }
    }
}