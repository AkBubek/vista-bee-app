package com.example.vistabee

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SettingPage  : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings)

        val settingBackBtn : ImageView = findViewById(R.id.settingBackBtn)
        settingBackBtn.setOnClickListener{
            startActivity(Intent(this, ProfilePage::class.java))
        }

        val logOutTxt : TextView = findViewById(R.id.logOutTxt)
        logOutTxt.setOnClickListener {
            startActivity(Intent(this, FourthActivity::class.java))
        }

    }
}