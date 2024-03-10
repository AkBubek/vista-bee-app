package com.example.vistabee

import android.app.DatePickerDialog // Import DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.vistabee.R // Import the correct R class generated for your project
import java.util.Calendar

class ExpPage  : AppCompatActivity() {
    private var editTextDate: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ex)

        val backExpBtn = findViewById<ImageView>(R.id.exBackBtn)
        editTextDate = findViewById<EditText>(R.id.editTextDate)

        backExpBtn.setOnClickListener {
            val intent = Intent(this, ProfilePage::class.java)
            startActivity(intent)
        }

        // Set OnClickListener to show DatePickerDialog when EditText is clicked
        editTextDate?.setOnClickListener {
            showDatePickerDialog()
        }
    }

    private fun showDatePickerDialog() {
        // Get current date
        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val dayOfMonth = calendar[Calendar.DAY_OF_MONTH]

        // Create DatePickerDialog and set listener to handle date selection
        val datePickerDialog = DatePickerDialog(this,
            { view, year, month, dayOfMonth -> // Display the selected date in EditText
                val selectedDate = "$dayOfMonth/${month + 1}/$year"
                editTextDate?.setText(selectedDate)
            }, year, month, dayOfMonth
        )

        // Show the DatePickerDialog
        datePickerDialog.show()
    }
}