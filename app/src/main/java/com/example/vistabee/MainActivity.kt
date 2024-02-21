package com.example.vistabee

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first)

        // Создайте объект Handler
        val handler = Handler()

        // Создайте отложенную задачу для перехода на вторую страницу через 5 секунд
        handler.postDelayed({
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
            finish() // Опционально, чтобы закрыть текущую активность после перехода
        }, 3000)
    }
}
