package com.example.vistabee

import android.app.Activity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory



class ProfilePage : AppCompatActivity() {

    private lateinit var imageView: ImageView

    // Объявляем переменную resultLauncher как член класса
    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val selectedImageUri = data?.data
                // Устанавливаем выбранное изображение в ImageView и применяем круглую обводку
                setRoundedImage(selectedImageUri)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ninth)

        val eduBtn = findViewById<Button>(R.id.educationBtn)
        val expBtn = findViewById<Button>(R.id.experienceBtn)
        val sklBtn = findViewById<Button>(R.id.skillsBtn)
        val sumBtn = findViewById<Button>(R.id.summaryBtn)
        val setBtn = findViewById<ImageView>(R.id.settingBtn)
        val backBtn = findViewById<ImageView>(R.id.backButTonn)

        eduBtn.setOnClickListener {
            val intent = Intent(this, EduPage::class.java)
            startActivity(intent)         }
        expBtn.setOnClickListener {
            val intent = Intent(this, ExpPage::class.java)
            startActivity(intent)         }
        sklBtn.setOnClickListener {
            val intent = Intent(this, SkillsPage::class.java)
            startActivity(intent)         }
        sumBtn.setOnClickListener {
            val intent = Intent(this, SummaryPage::class.java)
            startActivity(intent)         }
        setBtn.setOnClickListener {
            val intent = Intent(this, SettingPage::class.java)
            startActivity(intent)
        }
        backBtn.setOnClickListener {
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }

        imageView  = findViewById(R.id.imageView70)

        // Назначаем обработчик нажатия на ImageView
        imageView.setOnClickListener {
            openGalleryForImage()
        }
    }

    // Функция открытия галереи для выбора изображения
    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        resultLauncher.launch(intent)
    }

    // Функция для установки круглой обводки
    private fun setRoundedImage(imageUri: Uri?) {
        imageUri?.let {
            val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, imageUri)
            val roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(resources, bitmap)
            roundedBitmapDrawable.isCircular = true
            imageView.setImageDrawable(roundedBitmapDrawable)
        }
    }
}

