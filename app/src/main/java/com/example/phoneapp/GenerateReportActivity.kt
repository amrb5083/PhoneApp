// GenerateReportActivity.kt
package com.example.phoneapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneapp.databinding.ActivityGenerateReportBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class GenerateReportActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGenerateReportBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGenerateReportBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Additional logic for the Generate Report page
        binding.backToHomepageButton.setOnClickListener {
            val intent = Intent(this@GenerateReportActivity, HomepageActivity::class.java)
            startActivity(intent)
            finish() // Optional: Close the current activity if you don't want it in the back stack
        }

        // Retrieve the average speed from SpeedDataManager
        val averageSpeed = SpeedDataManager.averageSpeed

        // Display the average speed on the screen
        binding.generateReportContent.text = "Average Speed: ${String.format("%.2f", averageSpeed)} km/h"

        // Calculate the driving score based on the average speed
        val drivingScore = calculateDrivingScore(averageSpeed)

        // Display the driving score on the screen
        binding.drivingScoreContent.text = "Driving Score: $drivingScore"

        // Additional logging for debugging
        println("Average Speed: $averageSpeed, Driving Score: $drivingScore")
    }

    private fun calculateDrivingScore(averageSpeed: Double): Int {
        if (averageSpeed < 20 || averageSpeed > 100) {
            return 0
        } else if (averageSpeed in 20.0..35.0) {
            return 10
        } else if (averageSpeed in 35.0..40.0) {
            return 25
        } else if (averageSpeed in 40.0..49.0) {
            return 80
        } else if (averageSpeed in 50.0..52.0) {
            return 100
        } else if (averageSpeed in 55.0..60.0) {
            return 60
        } else if (averageSpeed in 61.0..70.0) {
            return 40
        } else if (averageSpeed in 71.0..75.0) {
            return 20
        } else if (averageSpeed >= 76) {
            return 0
        } else {
            return 0
        }
    }
}
