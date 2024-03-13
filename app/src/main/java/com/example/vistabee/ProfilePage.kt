package com.example.vistabee


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.storage.FirebaseStorage

class ProfilePage : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var currentUser: FirebaseUser
    private lateinit var imageView: ImageView

    private var storageReference = FirebaseStorage.getInstance().reference
    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
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
        val expBtn = findViewById<Button>(R.id.experienceBtn)
        val sklBtn = findViewById<Button>(R.id.skillsBtn)
        val sumBtn = findViewById<Button>(R.id.summaryBtn)
        val setBtn = findViewById<ImageView>(R.id.settingBtn)
        val backBtn = findViewById<ImageView>(R.id.backButTonn)
        val profBtn = findViewById<ImageView>(R.id.profileP)

        eduBtn.setOnClickListener {
            val intent = Intent(this, EduPage::class.java)
            startActivity(intent)
        }
        expBtn.setOnClickListener {
            val intent = Intent(this, ExpPage::class.java)
            startActivity(intent)
        }
        sklBtn.setOnClickListener {
            val intent = Intent(this, SkillsPage::class.java)
            startActivity(intent)
        }
        sumBtn.setOnClickListener {
            val intent = Intent(this, SummaryPage::class.java)
            startActivity(intent)
        }
        setBtn.setOnClickListener {
            val intent = Intent(this, SettingPage::class.java)
            startActivity(intent)
        }
        backBtn.setOnClickListener {
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }

        imageView = findViewById(R.id.imageView70)
        imageView.setOnClickListener {
            openGalleryForImage()
        }

        loadProfileImage()

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
                    saveProfileImageUri(uri)

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

    private fun saveProfileImageUri(imageUri: Uri?) {
        imageUri?.let {
            val sharedPref = getSharedPreferences("MyPrefs", MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("profile_image_uri", it.toString())
                apply()
            }
        }
    }

    private fun loadProfileImage(): Uri? {
        val sharedPref = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val savedUriString = sharedPref.getString("profile_image_uri", null)
        return savedUriString?.let { Uri.parse(it) }
    }



}
