package com.example.vistabee

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.auth
import com.google.firebase.database.FirebaseDatabase


class FifthActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var regUserName : EditText

    private  lateinit var regEmail : TextInputEditText

    private  lateinit var regPassword : EditText

    private lateinit var signUpBtn : Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fifth)

        firebaseAuth = FirebaseAuth.getInstance()

        regUserName = findViewById(R.id.userName)
        regEmail = findViewById(R.id.email)
        regPassword = findViewById(R.id.password)



        signUpBtn = findViewById(R.id.sign_up_btn)

        signUpBtn.setOnClickListener {

            val username = regUserName.text.toString()
            val email = regEmail.text.toString()
            val password = regPassword.text.toString()

            if (TextUtils.isEmpty(email)||TextUtils.isEmpty(password)||TextUtils.isEmpty(username)){
                Toast.makeText(this, "One or more fields are empty", Toast.LENGTH_SHORT).show();
            } else {
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Успешная регистрация пользователя
                            Toast.makeText(baseContext, "Account Created.", Toast.LENGTH_SHORT).show()

                            // Получение текущего пользователя
                            val user = firebaseAuth.currentUser

                            // Создание объекта с новым именем пользователя
                            val profileUpdates = UserProfileChangeRequest.Builder()
                                .setDisplayName(username) // Здесь устанавливается имя пользователя
                                .build()

                            // Обновление профиля пользователя
                            user?.updateProfile(profileUpdates)
                                ?.addOnCompleteListener { updateTask ->
                                    if (updateTask.isSuccessful) {
                                        Log.d(TAG, "User profile updated.")
                                    } else {
                                        Log.e(TAG, "Failed to update user profile.")
                                    }
                                }

                            // Переход на домашнюю страницу
                            startActivity(Intent(this, HomePage::class.java))
                        } else {
                            // Если регистрация не удалась
                            Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                        }
                    }


            }



        }


        val logTxt = findViewById<TextView>(R.id.login_text)

        logTxt.setOnClickListener{
            val intent = Intent(this, FourthActivity::class.java)
            startActivity(intent)
        }
    }
}
