package com.example.lec7

import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button

class ImplicitActivity : AppCompatActivity() {
    private lateinit var buttonPhoneCall: Button
    private lateinit var buttonSearch: Button
    private lateinit var buttonSendMessage: Button
    private lateinit var buttonViewPicture: Button
    private lateinit var buttonViewContact: Button
    private lateinit var buttonMaps: Button
    private lateinit var buttonMusic: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit)

        buttonPhoneCall = findViewById<Button>(R.id.btnPhoneCall)
        buttonSearch = findViewById<Button>(R.id.btnSearch)
        buttonSendMessage = findViewById<Button>(R.id.btnMessage)
        buttonViewPicture = findViewById<Button>(R.id.btnViewPicture)
        buttonViewContact = findViewById<Button>(R.id.btnViewContact)
        buttonMaps = findViewById<Button>(R.id.btnMap)
        buttonMusic = findViewById<Button>(R.id.btnMusic)


        buttonPhoneCall.setOnClickListener {
            phoneCall()
        }

        buttonSearch.setOnClickListener {
            search()
        }

        buttonSendMessage.setOnClickListener {
            sendMessage()
        }

        buttonViewPicture.setOnClickListener {
            viewPicture()
        }

        buttonViewContact.setOnClickListener {
            viewContact()
        }

        buttonMaps.setOnClickListener {
            map()
        }

        buttonMusic.setOnClickListener {
            playMusic()
        }
    }

    private fun playMusic() {
        val intent = Intent(MediaStore.INTENT_ACTION_MUSIC_PLAYER)
        startActivity(intent)

    }

    private fun map() {
        val geoCode = "geo:0,0?q=1860+east+18th+street+cleveland+oh"
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(geoCode)
        )
        startActivity(intent)
    }

    private fun viewContact() {
        val myData = "content://contacts/people/"
        val myActivity2 = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(myData)
        )
        startActivity(myActivity2)
    }

    private fun viewPicture() {
        val myIntent = Intent()

        myIntent.setType("image/pictures/*")
        myIntent.setAction(Intent.ACTION_GET_CONTENT)
        startActivity(myIntent)

    }

    private fun sendMessage() {
        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("sms:5551234"));

        intent.putExtra("sms body", "are we playing golf next Saturday?");

        startActivity(intent);

    }

    private fun phoneCall() {
        var myActivity2: Intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel: 555-1234"))
        startActivity(myActivity2)
    }

    private fun search() {
        val intent: Intent = Intent(Intent.ACTION_WEB_SEARCH)
        intent.putExtra(SearchManager.QUERY, "straight hitting golf clubs")
        startActivity(intent)

    }
}