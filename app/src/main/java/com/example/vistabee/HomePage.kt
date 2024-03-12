package com.example.vistabee

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomePage : AppCompatActivity() {

    private lateinit var imageViewClick: ImageView
    private lateinit var spot: ImageView
    private lateinit var appl: ImageView
    private lateinit var pin: ImageView
    private lateinit var editTextSearch: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)

        val userNameTextView = findViewById<TextView>(R.id.userName)

        // Получаем имя пользователя из Intent
        val userName = intent.getStringExtra("userName") ?: ""
        userNameTextView.text = userName // Установка имени пользователя в TextView



        val readBtn = findViewById<Button>(R.id.readmorebtn)
        readBtn.setOnClickListener {
            val intent = Intent(this, ReadMore::class.java)
            startActivity(intent)
        }

        val fullbtn = findViewById<ImageView>(R.id.imageViewClick)
        fullbtn.setOnClickListener {
            val intent = Intent(this, Fulltime::class.java)
            startActivity(intent)
        }

        val rembtn = findViewById<ImageView>(R.id.imageViewClick1)
        rembtn.setOnClickListener {
            val intent = Intent(this, Remote1::class.java)
            startActivity(intent)
        }

        val profilePicture = findViewById<ImageView>(R.id.profileP)
        profilePicture.setOnClickListener {
            startActivity(Intent(this, ProfilePage::class.java))
        }

        val imageViewGoogle = findViewById<ImageView>(R.id.imageView21)
        imageViewGoogle.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://meet.google.com/?pli=1"))
            startActivity(intent)
        }




        }

    }


