package com.example.vistabee

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
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
    private lateinit var regSpeciality : Spinner
    private lateinit var regCourse : Spinner
    private lateinit var regGroup : Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fifth)


        regSpeciality = findViewById(R.id.specialitySpinner)
        val specialitiesArray = resources.getStringArray(R.array.specialities)
        val specialityAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, specialitiesArray)
        specialityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        regSpeciality.adapter = specialityAdapter

        regCourse = findViewById(R.id.courseSpinner)
        val coursesArray = resources.getStringArray(R.array.course)
        val coursesAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, coursesArray)
        coursesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        regCourse.adapter = coursesAdapter

        regGroup = findViewById(R.id.groupSpinner)
        val groupsArray = resources.getStringArray(R.array.groups)
        val groupsAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, groupsArray)
        groupsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        regGroup.adapter = groupsAdapter

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

            // Получение выбранных значений из спиннеров
            val speciality = regSpeciality.selectedItem.toString()
            val course = regCourse.selectedItem.toString()
            val group = regGroup.selectedItem.toString()

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(username) || TextUtils.isEmpty(
                    lastname
                ) || TextUtils.isEmpty(phoneNumber)
            ) {
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
                                userSpeciality = speciality,
                                userCourse = course,
                                userGroup = group
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
                            Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show()
                        }
                    }

                val logTxt = findViewById<TextView>(R.id.login_text)
        logTxt.setOnClickListener{
            val intent = Intent(this, FourthActivity::class.java)
            startActivity(intent)
        }
         }
        }
    }
}
