package com.example.phoneapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DatabaseHelper(this)

        val usernameEditText: EditText = findViewById(R.id.usernameEditText)
        val passwordEditText: EditText = findViewById(R.id.passwordEditText)
        val loginButton: Button = findViewById(R.id.loginButton)
        val createAccountButton: Button = findViewById(R.id.createAccountButton)

        // Existing logic for login button
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            val user = db.getUser(username)

            if (user != null && user.password == password) {
                showToast("Login Successful")

                // Redirect to the homepage after successful login
                val intent = Intent(this, HomepageActivity::class.java)
                startActivity(intent)
                finish() // Optional: Close the login activity
            } else {
                showToast("Invalid username or password")
            }
        }

        // Existing logic for createAccountButton (with corrected indentation)
        createAccountButton.setOnClickListener {
            // Corrected indentation for existing logic
            /*
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            val existingUser = db.getUser(username)

            if (existingUser == null) {
                // User doesn't exist, proceed with registration
                val result = db.addUser(username, password)

                if (result != -1L) {
                    showToast("Account created successfully")
                } else {
                    showToast("Failed to create account")
                }
            } else {
                // User already exists, show a message or handle accordingly
                showToast("Username already exists. Please choose a different username.")
            }
            */

            // New logic for createAccountButton
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
