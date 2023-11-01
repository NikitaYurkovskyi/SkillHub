package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.myapplication.databinding.OwnProfilePageBinding

class OwnProfilePageActivity : AppCompatActivity() {
    lateinit var binding : OwnProfilePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = OwnProfilePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var changeAccountButton = binding.changeAccountButton
        changeAccountButton.setOnClickListener{
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        var backButton = binding.backButton
        backButton.setOnClickListener{
            var intent = Intent(this, ChatsPageActivity::class.java)
            startActivity(intent)
        }
    }
}