package com.example.vistabee

import android.content.Intent
import android.os.Bundle
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.third)

        // Инициализируем кнопку по ее идентификатору
        val button = findViewById<Button>(R.id.button)
        // Назначаем обработчик нажатия кнопки
        button.setOnClickListener {
            val intent = Intent(this, FourthActivity::class.java)
            startActivity(intent)
        }
        val slideOne = findViewById<ImageView>(R.id.slide1)
        val slideTwo = findViewById<ImageView>(R.id.slide2)
        val slideThree = findViewById<ImageView>(R.id.slide3)

        val slideAnimationOne = TranslateAnimation(100f, 0f, 0f, 0f)
        slideAnimationOne.duration = 6000
        slideOne.startAnimation(slideAnimationOne)

        val slideAnimationTwo = TranslateAnimation(1f, 0f, 0f, 0f)
        slideAnimationTwo.duration = 40
        slideTwo.startAnimation(slideAnimationTwo)

        val slideAnimationThree = TranslateAnimation(100f, 0f, 0f, 0f)
        slideAnimationThree.duration = 6000
        slideThree.startAnimation(slideAnimationThree)
    }
}
