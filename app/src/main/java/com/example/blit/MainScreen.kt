package com.example.blit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window

class MainScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        supportActionBar?.hide()
        setContentView(R.layout.activity_main_screen)
    }
}