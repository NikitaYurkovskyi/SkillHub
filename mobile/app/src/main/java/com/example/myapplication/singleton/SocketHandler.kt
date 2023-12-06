package com.example.myapplication.singleton

import android.util.Log
import com.example.myapplication.BuildConfig
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException
import java.util.Collections.singletonList
import java.util.Collections.singletonMap
import org.json.JSONObject


object SocketHandler {
    private var mSocket: Socket? = null

    @Synchronized
    fun setSocket(accessToken: String){
        try {
            Log.i("Socket", "Access Token: $accessToken")
            var options = IO.Options
                .builder()
                .setExtraHeaders(singletonMap("authorization", singletonList("Bearer $accessToken")))
                .build()

            mSocket = IO.socket(BuildConfig.SOCKET_URL, options)

            mSocket?.on(Socket.EVENT_CONNECT) {
                val authorization = "Bearer $accessToken"
                Log.i("Socket", "Socket has been connected")
            }
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

}