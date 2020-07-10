package com.example.tracking_corona

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class ActivityBrowser: AppCompatActivity(){

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browser)
        setTitle("Thông tin phòng bệnh");
        webView = findViewById(R.id.webView)
        val url = intent.getStringExtra("url")
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(url)
    }
}