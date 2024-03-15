package com.example.vistabee

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class ProfileStatic : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var currentUser: FirebaseUser
    private lateinit var profilePicture: ImageView
    private lateinit var editIcon : ImageView

    private lateinit var firstNameTextView: TextView
    private lateinit var lastNameTextView: TextView
    private lateinit var phoneNumberTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var summaryTextView: TextView
    private lateinit var gpaTextView: TextView
    private lateinit var eduTextView: TextView
    private lateinit var skillsTextView: TextView

    private lateinit var groupTextView: TextView
    private lateinit var courseTextView: TextView
    private lateinit var specialityTextView: TextView

    private lateinit var backBtn: TextView

    private lateinit var uploadPdfButton: Button
    private lateinit var pdfWebView: WebView

    private var storageReference = FirebaseStorage.getInstance().reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profilez)

        uploadPdfButton = findViewById(R.id.uploadPdfButton)
        pdfWebView = findViewById(R.id.pdfWebView)

        firebaseAuth = FirebaseAuth.getInstance()
        currentUser = firebaseAuth.currentUser ?: return

        firstNameTextView = findViewById(R.id.firstName)
        lastNameTextView = findViewById(R.id.lastName)
        phoneNumberTextView = findViewById(R.id.phoneNumber)
        emailTextView = findViewById(R.id.email)
        summaryTextView = findViewById(R.id.summaryText)
        gpaTextView = findViewById(R.id.gpa)
        eduTextView = findViewById(R.id.collegeInfo)
        skillsTextView = findViewById(R.id.skills)
        groupTextView = findViewById(R.id.group)
        courseTextView = findViewById(R.id.course)
        specialityTextView = findViewById(R.id.speciality)

        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val pdfUri = data?.data
                pdfUri?.let { uri ->
                    uploadPdfToFirebaseStorage(uri)
                }
            } else {
                Toast.makeText(this, "PDF selection cancelled", Toast.LENGTH_SHORT).show()
            }
        }

        uploadPdfButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "application/pdf"
            resultLauncher.launch(intent)
        }

        val usersRef = FirebaseDatabase.getInstance().getReference("users").child(currentUser.uid)
        usersRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val user = snapshot.getValue(User::class.java)
                    user?.let {
                        firstNameTextView.text = it.userName
                        lastNameTextView.text = it.lastName
                        phoneNumberTextView.text = it.phoneNumber
                        emailTextView.text = it.email
                        summaryTextView.text = it.userSummary
                        gpaTextView.text = it.userGpa
                        eduTextView.text = it.userEdu
                        skillsTextView.text = it.userSkills
                        groupTextView.text = it.userGroup
                        courseTextView.text = it.userCourse
                        specialityTextView.text = it.userSpeciality
                    }
                } else {
                    Toast.makeText(this@ProfileStatic, "User data not found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ProfileStatic, "Failed to retrieve user data", Toast.LENGTH_SHORT).show()
            }
        })

        editIcon = findViewById(R.id.editIcon)
        editIcon.setOnClickListener{startActivity(Intent(this, ProfilePage::class.java))}

        profilePicture = findViewById(R.id.profilePictureStatic)
        loadProfileImage()

        backBtn = findViewById(R.id.backBtn)
        backBtn.setOnClickListener { startActivity(Intent(this, HomePage::class.java))}
    }

    private fun loadProfileImage() {
        currentUser.uid.let { userId ->
            val userDataRef = FirebaseDatabase.getInstance().getReference("users").child(userId)
            userDataRef.child("userProfilePicUrl").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val userProfilePicUrl = snapshot.value as? String
                    userProfilePicUrl?.let { url ->
                        Glide.with(this@ProfileStatic)
                            .load(url)
                            .circleCrop()
                            .into(profilePicture)
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@ProfileStatic, "Failed to load profile image", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun uploadPdfToFirebaseStorage(pdfUri: Uri) {
        val userId = currentUser.uid
        val pdfRef = storageReference.child("pdf_files").child("$userId.pdf")

        val uploadTask = pdfRef.putFile(pdfUri)
        uploadTask.addOnSuccessListener { taskSnapshot ->
            pdfRef.downloadUrl.addOnSuccessListener { uri ->
                val pdfUrl = uri.toString()
                savePdfUrlToDatabase(pdfUrl)
            }
        }.addOnFailureListener { exception ->
            // Обработка ошибки при загрузке PDF
            Toast.makeText(this, "PDF upload failed: ${exception.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun savePdfUrlToDatabase(pdfUrl: String) {
        val userId = currentUser.uid
        val userDataRef = FirebaseDatabase.getInstance().getReference("users").child(userId)
        userDataRef.child("userCvUrl").setValue(pdfUrl)
        // Отображение PDF в WebView
        pdfWebView.visibility = View.VISIBLE
        pdfWebView.settings.javaScriptEnabled = true
        pdfWebView.loadUrl("https://docs.google.com/gview?embedded=true&url=$pdfUrl")
    }
}
