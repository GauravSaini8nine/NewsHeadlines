package com.gaurav.newsheadlines.Activity

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gaurav.newsheadlines.R

class WebViewActivity : AppCompatActivity() {
    lateinit var webview: WebView


    var urlLink: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        webview = findViewById(R.id.webview)



        if (intent != null) {
            urlLink = intent.getStringExtra("urlLink")
            webview.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    url: String?
                ): Boolean {
                    if (url != null) {


                        view?.loadUrl(url)
                    } else {
                        Toast.makeText(
                            this@WebViewActivity,
                            "Something went wrong.\n Please try again later",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    return true
                }
            }

            urlLink?.let { webview.loadUrl(it) }
            webview.settings.javaScriptEnabled = true
            webview.settings.allowContentAccess = true
            webview.settings.domStorageEnabled = true
            webview.settings.useWideViewPort = true
            webview.settings.setAppCacheEnabled(true)


        } else {
            Toast.makeText(
                this,
                "Something went wrong.\n Please try again later",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}