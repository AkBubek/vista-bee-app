package com.example.vistabee

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class FifthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fifth)

        val logTxt = findViewById<TextView>(R.id.login_text)

        logTxt.setOnClickListener{
            val intent = Intent(this, FourthActivity::class.java)
            startActivity(intent)
        }

        val userLogin : EditText = findViewById(R.id.userName)
        val userEmail : TextInputEditText = findViewById(R.id.email)
        val userPassword : EditText = findViewById(R.id.password)

        val signUpBtn = findViewById<Button>(R.id.sign_up_btn)

        signUpBtn.setOnClickListener{
            val intent = Intent(this, FourthActivity::class.java)
            val login = userLogin.text.toString().trim()
            val email = userEmail.text.toString().trim()
            val password = userPassword.text.toString().trim()


            if(login == "" || email == "" || password == "")
                Toast.makeText(this, "One or more fields are empty please check again", Toast.LENGTH_LONG).show()
            else{
                val user = User(login, email, password)

                val db = DbHelper(this, null)
                db.addUser(user)
                Toast.makeText(this, "Registered $login successfully", Toast.LENGTH_LONG).show()


                startActivity(intent)

                userLogin.text.clear()
                userEmail.text?.clear()
                userPassword.text.clear()
            }
        }


    }
}