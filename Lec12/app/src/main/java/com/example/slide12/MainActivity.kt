package com.example.slide12

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSensor = findViewById<Button>(R.id.btnSensor)
        val btnWifi = findViewById<Button>(R.id.btnWifi)
        val btnTele = findViewById<Button>(R.id.btnTelephone)
        val btnCamera = findViewById<Button>(R.id.btnCamera)

        btnSensor.setOnClickListener {
            val intent = Intent(this, SensorActivity::class.java)
            startActivity(intent)
        }
        btnWifi.setOnClickListener {
            val intent = Intent(this, WifiActivity::class.java)
            startActivity(intent)
        }
        btnCamera.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }
    }
}