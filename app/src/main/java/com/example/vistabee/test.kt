package com.example.vistabee

import android.annotation.SuppressLint
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

class test : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ninth)

        val eduBtn = findViewById<Button>(R.id.educationBtn)
        val expBtn = findViewById<Button>(R.id.experienceBtn)
        val sklBtn = findViewById<Button>(R.id.skillsBtn)
        val sumBtn = findViewById<Button>(R.id.summaryBtn)
        val setBtn = findViewById<ImageView>(R.id.settingBtn)
        val backBtn = findViewById<ImageView>(R.id.backButTonn)
        val profBtn = findViewById<ImageView>(R.id.profile_picture)

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
        backBtn.setOnClickListener {
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }
        profBtn.setOnClickListener {
            val intent = Intent(this, ProfilePage::class.java)
            startActivity(intent)
        }

        }


    }
