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

                // После успешной аутентификации пользователя, сохраните его электронную почту в Intent
                if (isAuth) {
                    Toast.makeText(this, "Welcome back!", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, HomePage::class.java)
                    intent.putExtra("bazar@gmail.com", email)// Сохраняем электронную почту в Intent
                    userEmailInput.text?.clear()
                    userPasswordInput.text.clear()
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "User $email not found", Toast.LENGTH_LONG).show()
                }

            }
        }
    }
}
