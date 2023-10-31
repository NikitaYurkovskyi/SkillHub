package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class ChatsPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chats_page)

        var ownProfileButton = findViewById<ImageView>(R.id.viewOwnProfile)
        ownProfileButton.setOnClickListener{
            var intent = Intent(this, OwnProfilePageActivity::class.java)
            startActivity(intent)
        }
    }
}