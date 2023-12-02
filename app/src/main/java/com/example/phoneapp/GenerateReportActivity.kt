// GenerateReportActivity.kt
package com.example.phoneapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneapp.databinding.ActivityGenerateReportBinding

class GenerateReportActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGenerateReportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGenerateReportBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Additional logic for the Generate Report page

        binding.backToHomepageButton.setOnClickListener {
            startActivity(Intent(this, HomepageActivity::class.java))
            finish()
        }
    }
}
