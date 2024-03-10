package com.example.vistabee

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomePage : AppCompatActivity() {

    private lateinit var imageViewClick: ImageView
    private lateinit var spot: ImageView
    private lateinit var appl: ImageView
    private lateinit var pin: ImageView
    private lateinit var editTextSearch: EditText // Добавляем поле EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)

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

        val profilePicture = findViewById<ImageView>(R.id.profileP)
        profilePicture.setOnClickListener {
            startActivity(Intent(this, ProfilePage::class.java))
            val intent = Intent(this,ProfilePage::class.java)
            startActivity(intent)
        }

        val imageViewGoogle = findViewById<ImageView>(R.id.imageView21)
        imageViewGoogle.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://meet.google.com/?pli=1"))
            startActivity(intent)
        }

        // Получаем ссылки на ImageView
        imageViewClick = findViewById(R.id.imageViewClick)
        spot = findViewById(R.id.spot)
        appl = findViewById(R.id.appl)
        pin = findViewById(R.id.pin)

        // Получаем ссылку на EditText
        editTextSearch = findViewById(R.id.editTextText)

        // Устанавливаем слушатель для EditText, чтобы активировать поиск при нажатии на поле ввода
        editTextSearch.setOnClickListener {
            // Вызываем функцию обработки поискового запроса с текстом из EditText
            handleSearchQuery(editTextSearch.text.toString())
        }

        val profileImageUri = loadProfileImage()
        if (profileImageUri != null) {
            profilePicture.setImageURI(profileImageUri)
        }
    }

    private fun handleSearchQuery(query: String) {
        // Определяем, какие изображения отображать или скрывать в зависимости от запроса

        // Если запрос содержит "UX/UI Designer", показываем imageViewClick и скрываем остальные
        if (query.contains("UX/UI Designer", ignoreCase = true)) {
            imageViewClick.visibility = ImageView.VISIBLE
            spot.visibility = ImageView.GONE
            appl.visibility = ImageView.GONE
            pin.visibility = ImageView.GONE
        }
        // Если запрос содержит "quality assurance", показываем spot и скрываем остальные
        else if (query.contains("Quality Assurance", ignoreCase = true)) {
            imageViewClick.visibility = ImageView.GONE
            spot.visibility = ImageView.VISIBLE
            appl.visibility = ImageView.GONE
            pin.visibility = ImageView.GONE
        }
        // Если запрос содержит "business analyst", показываем appl и скрываем остальные
        else if (query.contains("Business Analyst", ignoreCase = true)) {
            imageViewClick.visibility = ImageView.GONE
            spot.visibility = ImageView.GONE
            appl.visibility = ImageView.VISIBLE
            pin.visibility = ImageView.GONE
        }
        // Если запрос содержит "sales & marketing", показываем appl (или другое изображение) и скрываем остальные
        else if (query.contains("Sales & Marketing", ignoreCase = true)) {
            imageViewClick.visibility = ImageView.GONE
            spot.visibility = ImageView.GONE
            appl.visibility = ImageView.VISIBLE
            pin.visibility = ImageView.GONE
        }
        // Если запрос содержит "writing & content", показываем pin (или другое изображение) и скрываем остальные
        else if (query.contains("Writing & Content", ignoreCase = true)) {
            imageViewClick.visibility = ImageView.GONE
            spot.visibility = ImageView.GONE
            appl.visibility = ImageView.GONE
            pin.visibility = ImageView.VISIBLE
        }
        // Если запрос не соответствует ни одному ключевому слову, скрываем все изображения
        else {
            imageViewClick.visibility = ImageView.GONE
            spot.visibility = ImageView.GONE
            appl.visibility = ImageView.GONE
            pin.visibility = ImageView.GONE
        }
    }

    private fun loadProfileImage(): Uri? {
        return null
    }
}
