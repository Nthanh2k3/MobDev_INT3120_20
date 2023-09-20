package com.example.lec7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private var fullname : String = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button_sendMessage)
        val textView = findViewById<TextView>(R.id.TextEditView)

        button.setOnClickListener {
            setContentView(R.layout.activity_greeting)
            var greetingView = findViewById<TextView>(R.id.MessageFromMainActivity)
            val intent = Intent(this,GreetingActivity::class.java)
            startActivity(intent)
            fullname = intent.getStringExtra("fullname").toString()
            greetingView.setText("Message"+ fullname.toString())
        }
    }

    override fun finish() {
        val intent : Intent = Intent(this,MainActivity::class.java)
        var feedback : String = "OK, Hello" + this.fullname + ".How are you?"
        intent.putExtra("feedback",feedback)

        setResult(RESULT_OK,intent)
        super.finish()
    }




}