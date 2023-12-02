// SettingsActivity.kt
package com.example.phoneapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneapp.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Additional logic for the Settings page

        binding.backToHomepageButton.setOnClickListener {
            startActivity(Intent(this, HomepageActivity::class.java))
            finish()
        }
    }
}
