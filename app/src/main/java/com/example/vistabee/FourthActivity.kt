package com.example.vistabee

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vistabee.databinding.ActivityFulltimeBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class FourthActivity : AppCompatActivity() {


    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var emailAuth : TextInputEditText

    private  lateinit var passAuth : EditText

    private  lateinit var loginBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fourth1)

        firebaseAuth = FirebaseAuth.getInstance()

        emailAuth = findViewById(R.id.email_auth)
        passAuth = findViewById(R.id.password_auth)
        loginBtn = findViewById(R.id.login_btn) // Добавляем инициализацию loginBtn

        loginBtn.setOnClickListener {
            val emailData = emailAuth.text.toString()
            val passData = passAuth.text.toString()

            firebaseAuth.signInWithEmailAndPassword(emailData, passData)
                .addOnSuccessListener {
                    startActivity(Intent(this, HomePage::class.java))
                    Toast.makeText(this, "Sign In successful", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Sign In failed", Toast.LENGTH_SHORT).show()
                }
        }

        val regTxt: TextView = findViewById(R.id.register_text)

        regTxt.setOnClickListener {
            startActivity(Intent(this, FifthActivity::class.java))
        }
    }

}

