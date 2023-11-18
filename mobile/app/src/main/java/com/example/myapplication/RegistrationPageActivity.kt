package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.RegistrationPageBinding

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