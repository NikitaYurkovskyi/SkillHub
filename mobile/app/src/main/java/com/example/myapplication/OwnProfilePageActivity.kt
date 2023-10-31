package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class OwnProfilePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.own_profile_page)

        var changeAccountButton = findViewById<Button>(R.id.changeAccountButton)
        changeAccountButton.setOnClickListener{
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        var backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener{
            var intent = Intent(this, ChatsPageActivity::class.java)
            startActivity(intent)
        }
    }
}