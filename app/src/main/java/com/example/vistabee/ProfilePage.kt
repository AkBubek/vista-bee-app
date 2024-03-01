package com.example.vistabee

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class ProfilePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ninth)

        // Инициализируем кнопку по ее идентификатору
        val eduBtn = findViewById<Button>(R.id.educationBtn)
        val expBtn = findViewById<Button>(R.id.experienceBtn)
        val sklBtn = findViewById<Button>(R.id.skillsBtn)
        val sumBtn = findViewById<Button>(R.id.summaryBtn)
        val setBtn = findViewById<ImageView>(R.id.settingBtn)

        // Назначаем обработчик нажатия кнопки
        eduBtn.setOnClickListener {
            val intent = Intent(this, EduPage::class.java)
            startActivity(intent)
        }
        expBtn.setOnClickListener {
            val intent = Intent(this, ExpPage::class.java)
            startActivity(intent)
        }
        sklBtn.setOnClickListener {
            val intent = Intent(this, SkillsPage::class.java)
            startActivity(intent)
        }
        sumBtn.setOnClickListener {
            val intent = Intent(this, SummaryPage::class.java)
            startActivity(intent)
        }
        setBtn.setOnClickListener {
            val intent = Intent(this, SettingPage::class.java)
            startActivity(intent)
        }
    }
}
