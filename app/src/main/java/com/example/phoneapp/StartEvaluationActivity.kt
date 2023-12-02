// StartEvaluationActivity.kt
package com.example.phoneapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneapp.databinding.ActivityStartEvaluationBinding

class StartEvaluationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartEvaluationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartEvaluationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Additional logic for the Start Evaluation page

        binding.backToHomepageButton.setOnClickListener {
            startActivity(Intent(this, HomepageActivity::class.java))
            finish()
        }
    }
}
