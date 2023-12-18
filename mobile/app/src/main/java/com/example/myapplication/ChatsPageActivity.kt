package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.ChatsPageBinding

class ChatsPageActivity : AppCompatActivity() {
    private lateinit var binding: ChatsPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ChatsPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding.userInfoContainer.layoutManager = LinearLayout(this)

        var ownProfileButton = binding.correspondenceGoToOwnProfile
        ownProfileButton.setOnClickListener{
            var intent = Intent(this, OwnProfilePageActivity::class.java)
            startActivity(intent)
        }
    }


}