// RegistrationActivity.kt
package com.example.phoneapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistrationActivity : AppCompatActivity() {

    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        db = DatabaseHelper(this)

        val usernameEditText: EditText = findViewById(R.id.usernameEditTextRegistration)
        val passwordEditText: EditText = findViewById(R.id.passwordEditTextRegistration)
        val registerButton: Button = findViewById(R.id.registerButton)
        val backToLoginButton: Button = findViewById(R.id.backToLoginButton)

        // Existing logic for registerButton (with corrected indentation)
        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                val existingUser = db.getUser(username)

                if (existingUser == null) {
                    val result = db.addUser(username, password)

                    if (result != -1L) {
                        showToast("Account created successfully")

                        db.removeEmptyEntries()

                        val intent = Intent(this, HomepageActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        showToast("Failed to create account")
                    }
                } else {
                    showToast("Username already exists. Please choose a different username.")
                }
            } else {
                showToast("Please enter both username and password")
            }
        }

        // New logic for backToLoginButton
        backToLoginButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
