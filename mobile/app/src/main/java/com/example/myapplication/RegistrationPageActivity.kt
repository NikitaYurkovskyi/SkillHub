package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.StateSet
import com.example.myapplication.databinding.RegistrationPageBinding
import com.example.myapplication.retrofit.AuthResponse
import com.example.myapplication.retrofit.MainApi
import com.example.myapplication.retrofit.UserDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegistrationPageActivity : AppCompatActivity() {
    lateinit var binding : RegistrationPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RegistrationPageBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val linkToAuthorization = binding.linkToAuthorization
        linkToAuthorization.setOnClickListener{
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }
}