package com.example.phoneapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneapp.databinding.ActivitySettingsBinding
import android.content.Context


class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DatabaseHelper(this)

        binding.buttonUpdate.setOnClickListener {
            val newUsername = binding.editTextNewUsername.text.toString().trim()
            val newPassword = binding.editTextNewPassword.text.toString().trim()

            if (newUsername.isEmpty() || newPassword.isEmpty()) {
                showToast("Username and password cannot be empty")
                return@setOnClickListener
            }

            val userId = getCurrentUserId()
            if (userId != -1 && dbHelper.updateUser(userId, newUsername, newPassword)) {
                showToast("Update successful")
            } else {
                showToast("Update failed")
            }
        }

        binding.backToHomepageButton.setOnClickListener {
            finish()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun getCurrentUserId(): Int {
        val sharedPref = getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        return sharedPref.getInt("LoggedInUserId", -1)
    }
}
