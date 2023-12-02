// HomepageActivity.kt
package com.example.phoneapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneapp.databinding.ActivityHomepageBinding

class HomepageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomepageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomepageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.startEvaluationButton.setOnClickListener {
            startActivity(Intent(this, StartEvaluationActivity::class.java))
        }

        binding.generateReportButton.setOnClickListener {
            startActivity(Intent(this, GenerateReportActivity::class.java))
        }

        binding.settingsButton.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }
}
