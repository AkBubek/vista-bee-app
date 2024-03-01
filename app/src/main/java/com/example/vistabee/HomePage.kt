package com.example.vistabee

import android.content.Intent
import android.os.Bundle
<<<<<<< HEAD
=======
import android.widget.Button
>>>>>>> faecb00b7da859200cc4122f8fa10854ff9b4418
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomePage  : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)

<<<<<<< HEAD
        val profilePicture = findViewById<ImageView>(R.id.profile_picture)

        profilePicture.setOnClickListener{
=======
        val profileBtn = findViewById<ImageView>(R.id.clickableprofile)

        profileBtn.setOnClickListener {
>>>>>>> faecb00b7da859200cc4122f8fa10854ff9b4418
            val intent = Intent(this, ProfilePage::class.java)
            startActivity(intent)
        }
    }
}

