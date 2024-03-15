package com.example.vistabee

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomePage : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var currentUser: FirebaseUser
    private lateinit var profilePicture: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)

        firebaseAuth = FirebaseAuth.getInstance()
        currentUser = firebaseAuth.currentUser ?: return

        val databaseRef = FirebaseDatabase.getInstance().getReference("users")

        databaseRef.child(currentUser.uid).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(User::class.java)
                val userName = user?.userName
                if (!userName.isNullOrEmpty()) {

                    val userNameTextView = findViewById<TextView>(R.id.userName)
                    userNameTextView.text = userName
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })



        val readBtn = findViewById<Button>(R.id.readmorebtn)
        readBtn.setOnClickListener {
            val intent = Intent(this, ReadMore::class.java)
            startActivity(intent)
        }

        val fullbtn = findViewById<ImageView>(R.id.imageViewClick)
        fullbtn.setOnClickListener {
            val intent = Intent(this, Fulltime::class.java)
            startActivity(intent)
        }

        val spotbtn = findViewById<ImageView>(R.id.spot)
        spotbtn.setOnClickListener {
            val intent = Intent(this, spotify::class.java)
            startActivity(intent)
        }

        val rembtn = findViewById<ImageView>(R.id.imageViewClick1)
        rembtn.setOnClickListener {
            val intent = Intent(this, Remote1::class.java)
            startActivity(intent)
        }

        profilePicture = findViewById(R.id.profileP)
        profilePicture.setOnClickListener {
            startActivity(Intent(this, ProfileStatic::class.java))
        }

        val imageViewGoogle = findViewById<ImageView>(R.id.imageView21)
        imageViewGoogle.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://meet.google.com/?pli=1"))
            startActivity(intent)
        }



        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        bottomNavigationView.selectedItemId = R.id.navigation_home

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    Toast.makeText(this, "Home already selected", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.navigation_dashboard -> {
                    // Переход на страницу Dashboard (ProfilePage)
                    val intent = Intent(this, ProfilePage::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.navigation_plus -> {
                    // Переход на другую страницу (если необходимо)
                    Toast.makeText(this, "Add a job", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, GetActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_message -> {
                    // Переход на другую страницу (если необходимо)
                    val intent = Intent(this, MessageActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "Messages ", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.navigation_notifications -> {
                    // Переход на другую страницу (если необходимо)
                    val intent = Intent(this, NotificationActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "Notifications ", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }


        loadProfileImage()
    }

    private fun loadProfileImage() {
        currentUser.uid.let { userId ->
            val userDataRef = FirebaseDatabase.getInstance().getReference("users").child(userId)
            userDataRef.child("userProfilePicUrl").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val userProfilePicUrl = snapshot.value as? String
                    userProfilePicUrl?.let { url ->
                        Glide.with(this@HomePage)
                            .load(url)
                            .circleCrop() // Применяем округление к изображению
                            .into(profilePicture)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Обработка ошибки при загрузке изображения
                    Toast.makeText(this@HomePage, "Failed to load profile image", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }


}


