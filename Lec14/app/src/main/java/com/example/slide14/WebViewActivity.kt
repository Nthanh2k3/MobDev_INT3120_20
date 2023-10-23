package com.example.slide14

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient

class WebViewActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        // Initialize the WebView
        webView = findViewById(R.id.webView)

        // Enable JavaScript (optional)
        webView.settings.javaScriptEnabled = true

        // Load a URL
        webView.loadUrl("https://www.google.com")

        // Handle URL navigation in WebView
        webView.webViewClient = WebViewClient()

        // Handle additional events (e.g., displaying the page title)
        webView.webChromeClient = object : WebChromeClient() {
            override fun onReceivedTitle(view: WebView?, title: String?) {
                supportActionBar?.title = title
            }
        }

    }

    // Handle back button press in WebView
    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}