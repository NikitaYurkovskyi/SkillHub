package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.myapplication.databinding.ChatsPageBinding

class ChatsPageActivity : AppCompatActivity() {
    lateinit var binding: ChatsPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ChatsPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var ownProfileButton = binding.viewOwnProfile
        ownProfileButton.setOnClickListener{
            var intent = Intent(this, OwnProfilePageActivity::class.java)
            startActivity(intent)
        }
    }
}