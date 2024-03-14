package com.example.vistabee


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import com.bumptech.glide.Glide
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class ProfilePage : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var currentUser: FirebaseUser
    private lateinit var imageView: ImageView

    private lateinit var summaryEdit : EditText
    private lateinit var firstNameEdit : EditText
    private lateinit var lastNameEdit : EditText
    private lateinit var phoneNumberEdit : EditText
    private lateinit var specialityEdit : EditText
    private lateinit var skillsEdit : EditText

    private var storageReference = FirebaseStorage.getInstance().reference
    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                val selectedImageUri = data?.data
                setRoundedImage(selectedImageUri)
            }
        }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ninth)

        firebaseAuth = FirebaseAuth.getInstance()
        currentUser = firebaseAuth.currentUser ?: return

        val eduBtn = findViewById<Button>(R.id.educationBtn)
        val saveBtn = findViewById<Button>(R.id.saveBtn)
        val setBtn = findViewById<ImageView>(R.id.settingBtn)
        val backBtn = findViewById<ImageView>(R.id.backButTonn)

        eduBtn.setOnClickListener {
            val intent = Intent(this, EduPage::class.java)
            startActivity(intent)
        }
        setBtn.setOnClickListener {
            val intent = Intent(this, SettingPage::class.java)
            startActivity(intent)
        }
        backBtn.setOnClickListener {
            val intent = Intent(this, ProfileStatic::class.java)
            startActivity(intent)
        }

        imageView = findViewById(R.id.imageView70)
        imageView.setOnClickListener {
            openGalleryForImage()
        }

        loadProfileImage()

        summaryEdit = findViewById(R.id.summaryEdit)
        firstNameEdit = findViewById(R.id.firstNameEdit)
        lastNameEdit = findViewById(R.id.lastNameEdit)
        phoneNumberEdit = findViewById(R.id.phoneNumberEdit)
        specialityEdit = findViewById(R.id.specialityEdit)
        skillsEdit = findViewById(R.id.skillsEdit)



        val databaseRef = FirebaseDatabase.getInstance().getReference("users")
        val currentUser = firebaseAuth.currentUser

        saveBtn.setOnClickListener {
            val summary = summaryEdit.text.toString()
            val firstName = firstNameEdit.text.toString()
            val lastName = lastNameEdit.text.toString()
            val phoneNumber = phoneNumberEdit.text.toString()
            val speciality = specialityEdit.text.toString()
            val skills = skillsEdit.text.toString()

            currentUser?.let { user ->
                val userId = user.uid

                if (summary.isNotEmpty()) {
                    databaseRef.child(userId).child("userSummarydddddddddddddddddd").setValue(summary)
                }
                if (firstName.isNotEmpty()) {
                    databaseRef.child(userId).child("firstName").setValue(firstName)
                }
                if (lastName.isNotEmpty()) {
                    databaseRef.child(userId).child("lastName").setValue(lastName)
                }
                if (phoneNumber.isNotEmpty()) {
                    databaseRef.child(userId).child("phoneNumber").setValue(phoneNumber)
                }
                if (speciality.isNotEmpty()) {
                    databaseRef.child(userId).child("userSpeciality").setValue(speciality)
                }
                if (skills.isNotEmpty()) {
                    databaseRef.child(userId).child("userSkills").setValue(skills)
                }
            }

            startActivity(Intent(this, ProfileStatic::class.java))
        }


    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        resultLauncher.launch(intent)
    }

    private fun setRoundedImage(imageUri: Uri?) {
        imageUri?.let {
            val imageName = "${currentUser.uid}.jpg"
            val imageRef = storageReference.child("profile_images").child(imageName)

            val uploadTask = imageRef.putFile(imageUri)
            uploadTask.addOnSuccessListener { taskSnapshot ->
                // Получение ссылки на загруженное изображение
                imageRef.downloadUrl.addOnSuccessListener { uri ->
                    // Сохранение ссылки в базе данных Firebase
                    currentUser.uid?.let { userId ->
                        val userDataRef = FirebaseDatabase.getInstance().getReference("users").child(userId)
                        userDataRef.child("userProfilePicUrl").setValue(uri.toString())
                    }

                    // Отображение изображения
                    val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, imageUri)
                    val roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(resources, bitmap)
                    roundedBitmapDrawable.isCircular = true
                    imageView.setImageDrawable(roundedBitmapDrawable)
                }.addOnFailureListener { exception ->
                    // Обработка ошибки при получении ссылки на изображение
                    Toast.makeText(this, "Image uri fetch failed", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener { exception ->
                // Обработка ошибки при загрузке изображения
                Toast.makeText(this, "Image upload failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadProfileImage() {
        currentUser.uid.let { userId ->
            val userDataRef = FirebaseDatabase.getInstance().getReference("users").child(userId)
            userDataRef.child("userProfilePicUrl").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val userProfilePicUrl = snapshot.value as? String
                    userProfilePicUrl?.let { url ->
                        Glide.with(this@ProfilePage)
                            .load(url)
                            .circleCrop()
                            .into(imageView)
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@ProfilePage, "Failed to load profile image", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }





}

