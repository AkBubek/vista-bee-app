package com.example.vistabee

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class ProfilePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ninth)

        // Инициализируем кнопку по ее идентификатору
        val eduBtn = findViewById<Button>(R.id.educationBtn)

        // Назначаем обработчик нажатия кнопки
        eduBtn.setOnClickListener {
            val intent = Intent(this, EduPage::class.java)
            startActivity(intent)
        }
    }
}
