package com.food.internship

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed({
            val mainIntent = Intent(this@SplashScreen, MainActivity::class.java)
            this@SplashScreen.startActivity(mainIntent)
            this@SplashScreen.finish()
        }, 2000)
    }
}