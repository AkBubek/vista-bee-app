package com.example.vistabee

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomePage  : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)


        val profilePicture = findViewById<ImageView>(R.id.profile_picture)

        profilePicture.setOnClickListener{
            val intent = Intent(this, ProfilePage::class.java)
            startActivity(intent)
    }
}}

