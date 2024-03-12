package com.example.vistabee

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SuccessActivity  : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.success)

        val homeBtn = findViewById<Button>(R.id.button8)

        homeBtn.setOnClickListener {
            val userName = intent.getStringExtra("userName") // Получаем имя пользователя из Intent
            val intent = Intent(this, HomePage::class.java)
            intent.putExtra("userName", userName) // Передаем имя пользователя в Intent
            startActivity(intent)
        }
        val cancelBtn = findViewById<Button>(R.id.button9)

        cancelBtn.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }
}