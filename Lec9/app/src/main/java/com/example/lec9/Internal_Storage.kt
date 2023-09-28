package com.example.lec9

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStreamReader

class Internal_Storage : AppCompatActivity() {
    private lateinit var editTextName: EditText
    private lateinit var buttonSave: Button
    private lateinit var textViewSavedName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preferences)

        editTextName = findViewById(R.id.editTextName)
        buttonSave = findViewById(R.id.buttonSave)
        textViewSavedName = findViewById(R.id.textViewSavedName)

        loadSavedName()

        buttonSave.setOnClickListener {
            val name = editTextName.text.toString()
            saveName(name)
            textViewSavedName.text = "Saved Name: $name"
        }
    }

    private fun saveName(name: String) {
        try {
            val fileOutputStream = openFileOutput("user_name.txt", MODE_PRIVATE)
            fileOutputStream.write(name.toByteArray())
            fileOutputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun loadSavedName() {
        try {
            val fileInputStream = openFileInput("user_name.txt")
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            val savedName = bufferedReader.readLine()
            fileInputStream.close()
            textViewSavedName.text = "Saved Name: $savedName"
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}