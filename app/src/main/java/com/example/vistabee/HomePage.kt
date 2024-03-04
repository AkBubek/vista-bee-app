package com.example.vistabee

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomePage : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)

        val navView: BottomNavigationView = findViewById(R.id.bottom_navigation_view)
        navView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    // Переход на домашнюю страницу (HomePage)
                    true
                }
                R.id.navigation_dashboard -> {
                    // Переход на страницу профиля (ProfilePage)
                    val intent = Intent(this, ProfilePage::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_plus -> {
                    val intent = Intent(this, ProfilePage::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_message -> {
                    // Обработка нажатия на элемент "Notifications"
                    // Переход на нужную страницу
                    true
                }
                R.id.navigation_notifications -> {
                    // Обработка нажатия на элемент "Notifications"
                    // Переход на нужную страницу
                    true
                }
                else -> false
            }
        }

        val readBtn = findViewById<Button>(R.id.readmorebtn)
        readBtn.setOnClickListener {
            val intent = Intent(this, ReadMore::class.java)
            startActivity(intent)
        }

        val fullbtn = findViewById<ImageView>(R.id.fulltimebtn)
        fullbtn.setOnClickListener {
            val intent = Intent(this, Fulltime::class.java)
            startActivity(intent)
        }

        val rembtn = findViewById<ImageView>(R.id.remotebtn)
        rembtn.setOnClickListener {
            val intent = Intent(this, Remote1::class.java)
            startActivity(intent)
        }

        val profilePicture = findViewById<ImageView>(R.id.profile_picture)
        profilePicture.setOnClickListener {
            openGalleryForImage() // Добавленная функция для открытия галереи
        }

        val imageViewGoogle = findViewById<ImageView>(R.id.imageView21)
        imageViewGoogle.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://meet.google.com/?pli=1"))
            startActivity(intent)
        }

        // Загрузка изображения профиля
        val profileImageUri = loadProfileImage()
        if (profileImageUri != null) {
            profilePicture.setImageURI(profileImageUri)
        }
    }

    // Функция для открытия галереи для выбора изображения профиля
    private fun openGalleryForImage() {
        // Реализация открытия галереи здесь
    }

    // Функция для загрузки изображения профиля
    private fun loadProfileImage(): Uri? {
        // Реализация загрузки изображения профиля здесь
        return null // Пример возврата null, замените на вашу реализацию
    }
}
