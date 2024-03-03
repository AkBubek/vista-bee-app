package com.example.vistabee

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomePage  : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)

        val readBtn = findViewById<Button>(R.id.readmorebtn)
        readBtn.setOnClickListener {
            val intent = Intent(this, ReadMore::class.java)
            startActivity(intent)         }

        val profilePicture = findViewById<ImageView>(R.id.profile_picture)

        profilePicture.setOnClickListener{
            val intent = Intent(this, ProfilePage::class.java)
            startActivity(intent)
    }
        val imageViewGoogle = findViewById<ImageView>(R.id.imageView21)

        imageViewGoogle.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://meet.google.com/?pli=1"))
            startActivity(intent)
        }
}}

