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
    private lateinit var regLastname : EditText
    private lateinit var regPhoneNumber: EditText
    private  lateinit var regEmail : TextInputEditText
    private  lateinit var regPassword : EditText
    private lateinit var signUpBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fifth)

        firebaseAuth = FirebaseAuth.getInstance()

        regUserName = findViewById(R.id.userName)
        regLastname = findViewById(R.id.lastName)
        regPhoneNumber = findViewById(R.id.phoneNumber)
        regEmail = findViewById(R.id.email)
        regPassword = findViewById(R.id.password)



        signUpBtn = findViewById(R.id.sign_up_btn)

        signUpBtn.setOnClickListener {

            val firebaseAuth = FirebaseAuth.getInstance()
            val databaseRef = FirebaseDatabase.getInstance().getReference("users")
            val username = regUserName.text.toString()
            val email = regEmail.text.toString()
            val password = regPassword.text.toString()
            val lastname = regLastname.text.toString()
            val phoneNumber = regPhoneNumber.text.toString()

            if (TextUtils.isEmpty(email)||TextUtils.isEmpty(password)||TextUtils.isEmpty(username)||TextUtils.isEmpty(lastname)||TextUtils.isEmpty(phoneNumber)){
                Toast.makeText(this, "One or more fields are empty", Toast.LENGTH_SHORT).show();
            } else {
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user = firebaseAuth.currentUser
                            Toast.makeText(this, "Successful Auth", Toast.LENGTH_SHORT).show()
                            val newUser = User(
                                userName = username,
                                lastName = lastname,
                                email = email,
                                phoneNumber = phoneNumber,

                            )
                            user?.uid?.let { userId ->
                                databaseRef.child(userId).setValue(newUser)
                                    .addOnSuccessListener {
                                        Toast.makeText(this, "Data saved Successfully", Toast.LENGTH_SHORT).show()
                                    }
                                    .addOnFailureListener { e ->
                                        Toast.makeText(this, "Could not save data", Toast.LENGTH_SHORT).show()
                                    }
                            }
                            startActivity(Intent(this, HomePage::class.java))
                        } else {
                            Toast.makeText(this,"Registration Failed",Toast.LENGTH_SHORT).show()
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
