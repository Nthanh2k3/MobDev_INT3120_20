package com.example.slide12

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.telephony.SmsManager
import android.telephony.TelephonyManager
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class TelephoneActivity : AppCompatActivity() {
    private lateinit var telephonyManager: TelephonyManager
    private lateinit var smsManager: SmsManager
    private lateinit var textViewDeviceInfo: TextView
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_telephone)

        var btnCall = findViewById<Button>(R.id.btnStartCall)
        var btnGetInfo = findViewById<Button>(R.id.btnGetInfo)

        btnCall.setOnClickListener {
            val whoYouGonnaCall = Intent(Intent.ACTION_DIAL)
            whoYouGonnaCall.setData(Uri.parse("tel:555-2368"))
            startActivity(whoYouGonnaCall)
        }

        textViewDeviceInfo = findViewById(R.id.textViewDeviceInfo)

        // Initialize the TelephonyManager
        telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        smsManager = SmsManager.getDefault()


        // Display phone information on button click
        btnGetInfo.setOnClickListener {
            getPhoneInfo()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getPhoneInfo() {
//        val countryCode = telephonyManager.simCountryIso
//        val simOperator = telephonyManager.simOperator
//        textViewDeviceInfo.text = "$countryCode\n $simOperator"
        val sendTo = "5551234"
        val myMessage = "Hello I am Minh Quang"
        smsManager.sendTextMessage(sendTo, null, myMessage, null, null)
    }


}