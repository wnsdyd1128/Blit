package com.example.blit

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.Window
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.blit.databinding.ActivityMainBinding
import java.net.URISyntaxException


class MainActivity : AppCompatActivity() {
    private lateinit var mainActivityBinding: ActivityMainBinding
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        val webView = mainActivityBinding.webView
        webView.settings.apply {
            javaScriptEnabled = true

            setSupportMultipleWindows(true)
            javaScriptCanOpenWindowsAutomatically = true
            loadWithOverviewMode = true
            useWideViewPort = true
            domStorageEnabled = true
            allowContentAccess = true
        }
        webView.webViewClient = WebViewClientClass()
        webView.run {
            loadUrl("192.168.0.19:3000/intro")
        }
        setContentView(mainActivityBinding.root)
    }


    inner class WebViewClientClass : WebViewClient(){
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
        }
        override fun shouldOverrideUrlLoading(view: WebView,request: WebResourceRequest): Boolean {
            Log.d("MyTAG", "First: " + request.url.toString())
            var url : String?  = request.url.toString()
            val mUrl = url?.replace("localhost", "192.168.0.19")
            Log.d("MYTAG", "Replace: " + url)

            if (request.url.scheme == "intent") {
                try {
                    // Intent 생성
                    val intent = Intent.parseUri(mUrl, Intent.URI_INTENT_SCHEME)
                    Log.d("MYTAG", intent.toString())
                    // 실행 가능한 앱이 있으면 앱 실행
                    if (intent.resolveActivity(packageManager) != null) {
                        startActivity(intent)
                        Log.d("MyTAG", "ACTIVITY: ${intent.`package`}")
                        return true
                    }

                    // Fallback URL이 있으면 현재 웹뷰에 로딩
                    val fallbackUrl = intent.getStringExtra("browser_fallback_url")
                    if (fallbackUrl != null) {
                        view.loadUrl(fallbackUrl)
                        Log.d("MyTAG", "FALLBACK: $fallbackUrl")
                        return true
                    }

                    Log.e("MyTAG", "Could not parse anythings")

                } catch (e: URISyntaxException) {
                    Log.e("MyTAG", "Invalid intent request", e)
                }
            }

            return false
        }
    }
}