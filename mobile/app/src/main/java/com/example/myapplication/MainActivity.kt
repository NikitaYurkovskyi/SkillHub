package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.authorization_page)

        val linkToRegistration = findViewById<TextView>(R.id.goToRegistration)

        linkToRegistration.setOnClickListener{
            var intent = Intent(this, RegistrationPageActivity::class.java)
            startActivity(intent)
        }

        val authorizedButton = findViewById<Button>(R.id.authorizedButton)
        authorizedButton.setOnClickListener{
            var intent = Intent(this, ChatsPageActivity::class.java)
            startActivity(intent)
        }
    }


}