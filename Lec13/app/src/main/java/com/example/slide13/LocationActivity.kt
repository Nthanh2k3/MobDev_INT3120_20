package com.example.slide13

import android.annotation.SuppressLint
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnSuccessListener

class LocationActivity : AppCompatActivity() {
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private lateinit var locationTextView: TextView
    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        locationTextView = findViewById(R.id.locationTextView)

        mFusedLocationClient.lastLocation
            .addOnSuccessListener(this, OnSuccessListener { location: Location? ->
                // Lấy vị trí tại thời điểm gần nhất, có thể null
                if (location != null) {
                    // Xử lý vị trí lấy được
                    val latitude = location.latitude
                    val longitude = location.longitude
                    val locationText = "Latitude: $latitude\nLongitude: $longitude"
                    locationTextView.text = "Location: $locationText"
                } else {
                    locationTextView.text = "Location: Not available"
                }
            })
    }
}