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

        val reg_text = findViewById<TextView>(R.id.register_text)

        reg_text.setOnClickListener {
            val intent = Intent(this, FifthActivity::class.java)
            startActivity(intent)
        }



        val logInBtn = findViewById<Button>(R.id.login_btn)
        val userEmail : TextInputEditText = findViewById(R.id.email_auth)
        val userPassword : EditText = findViewById(R.id.password_auth)

        logInBtn.setOnClickListener{
            val intent = Intent(this, HomePage::class.java)
            val email = userEmail.text.toString().trim()
            val password = userPassword.text.toString().trim()


            if( email == "" || password == "")
                Toast.makeText(this, "One or more fields are empty please check again", Toast.LENGTH_LONG).show()
            else{
                val db = DbHelper(this, null)
                val isAuth = db.getUser(email, password)

                if(isAuth){
                    Toast.makeText(this, "Welcome back!", Toast.LENGTH_LONG).show()
                    userEmail.text?.clear()
                    userPassword.text.clear()
                    startActivity(intent)
                } else
                    Toast.makeText(this, "User $email not found", Toast.LENGTH_LONG).show()

            }
        }

    }
}
