package com.example.phoneapp

import android.content.Intent
import android.content.Context
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

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            val user = db.getUser(username)

            if (user != null && user.password == password) {
                showToast("Login Successful")
                storeUserId(user.id)
                val intent = Intent(this, HomepageActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                showToast("Invalid username or password")
            }
        }

        createAccountButton.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun storeUserId(userId: Int) {
        val sharedPref = getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putInt("LoggedInUserId", userId)
            apply()
        }
    }
}
