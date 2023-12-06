// StartEvaluationActivity.kt
package com.example.phoneapp

import SpeedDataManager
import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.example.phoneapp.databinding.ActivityStartEvaluationBinding
import android.content.Intent
import kotlin.math.roundToInt

class StartEvaluationActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mapView: MapView
    private var googleMap: GoogleMap? = null
    private lateinit var binding: ActivityStartEvaluationBinding

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    private lateinit var speedTextView: TextView
    private lateinit var generateDataButton: Button
    private lateinit var clearDataButton: Button
    private lateinit var showDataButton: Button

    private lateinit var drivingDataHelper: DrivingDataDatabaseHelper
    private val allSpeeds = mutableListOf<Double>()
    private var currentTestNumber = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartEvaluationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mapView = binding.mapView
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        setupLocationRequest()

        // Initialize views and buttons
        speedTextView = findViewById(R.id.speedTextView)
        generateDataButton = findViewById(R.id.generateDataButton)
        clearDataButton = findViewById(R.id.clearDataButton)
        showDataButton = findViewById(R.id.showDataButton)

        // Initialize the DrivingDataDatabaseHelper
        drivingDataHelper = DrivingDataDatabaseHelper(this)

        // Add the click listener for the "Back to Homepage" button
        findViewById<Button>(R.id.backToHomepageButton).setOnClickListener {
            val intent = Intent(this, HomepageActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        // Add your code to handle generating data when the button is clicked
        generateDataButton.setOnClickListener {
            // Generate a list of 15 random speeds between 30 and 65 km/h
            val randomSpeeds = List(15) { (30..65).random().toDouble() }

            // Display the generated speeds in the speedTextView
            speedTextView.text = "Generated Speeds: ${randomSpeeds.joinToString(", ")}"

            // Insert the generated data into the database
            drivingDataHelper.insertTestData(currentTestNumber, randomSpeeds)

            // Increment the test number for the next data point
            currentTestNumber++

            // Calculate the average speed for the current set of data
            val averageSpeed = randomSpeeds.average()
            SpeedDataManager.setCombinedAverageSpeed(averageSpeed)
            // Print the generated speeds and average speed for debugging
            println("Generated Speeds: $randomSpeeds, Average Speed: $averageSpeed")
        }
        // Add your code for clearing all data when the button is clicked
        clearDataButton.setOnClickListener {
            // Clear the list of all speeds
            allSpeeds.clear()
        }

        // Add your code for showing all data and average speed when the button is clicked
        showDataButton.setOnClickListener {
            // Retrieve all data from the database
            val testDataList = drivingDataHelper.getAllData()

            // Calculate the combined average speed from allSpeeds
            if (testDataList.isNotEmpty()) {
                val combinedSpeeds = testDataList.flatMap { it.speeds }
                val combinedAverageSpeed = combinedSpeeds.average()

                SpeedDataManager.setCombinedAverageSpeed(combinedAverageSpeed)

                // Update the SpeedTextView with the combined average speed
                speedTextView.text = "Combined Average Speed: ${String.format("%.2f", combinedAverageSpeed)} km/h"
            } else {
                println("No data available.")
            }
        }
    }

    private fun setupLocationRequest() {
        locationRequest = LocationRequest.create().apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                for (location in locationResult.locations) {
                    val latLng = LatLng(location.latitude, location.longitude)

                    // Move the camera to the updated location
                    googleMap?.animateCamera(CameraUpdateFactory.newLatLng(latLng))
                    googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f)) // Adjust the zoom level

                    // Convert speed from m/s to km/h
                    val speedKmh = location.speed * 3.6

                    // Update the speedTextView with the new speed in km/h
                    speedTextView.text = "Speed: ${String.format("%.2f", speedKmh)} km/h"
                }
            }
        }
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        enableMyLocation()
    }

    private fun enableMyLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }
        googleMap?.isMyLocationEnabled = true
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
        startLocationUpdates()
    }

    private fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
        stopLocationUpdates()
    }

    private fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    override fun onDestroy() {
        mapView.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        mapView.onLowMemory()
        super.onLowMemory()
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }
}
