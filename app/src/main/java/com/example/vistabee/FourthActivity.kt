package com.example.vistabee

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class FourthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fourth1)

        val logInBtn = findViewById<Button>(R.id.login_btn)
        val userEmailInput : TextInputEditText = findViewById(R.id.email_auth)
        val userPasswordInput : EditText = findViewById(R.id.password_auth)

        val regTxt : TextView = findViewById(R.id.register_text)

        regTxt.setOnClickListener {
            startActivity(Intent(this, FifthActivity::class.java))
        }

        logInBtn.setOnClickListener {
            val email = userEmailInput.text.toString().trim()
            val password = userPasswordInput.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email or password is empty", Toast.LENGTH_LONG).show()
            } else {
                val dbHelper = DbHelper(this, null)
                val isAuth = dbHelper.getUser(email, password)
                // После успешной аутентификации пользователя, получите его имя из базы данных
                if (isAuth) {
                    val userName = dbHelper.getUserName(email)
                    // Создаем Intent и передаем имя пользователя на HomePage
                    val intent = Intent(this, SuccessActivity::class.java)
                    intent.putExtra("userName", userName) // Передача имени пользователя
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "User $email not found", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, FailActivity::class.java))
                }

            }
        }
    }
}

