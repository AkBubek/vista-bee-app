package com.example.vistabee

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FifthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fifth)

        val logTxt = findViewById<TextView>(R.id.login_text)

        logTxt.setOnClickListener{
            val intent = Intent(this, FourthActivity::class.java)
            startActivity(intent)
        }

        val signUpBtn = findViewById<Button>(R.id.sign_up_btn)

        signUpBtn.setOnClickListener{
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }
    }
}