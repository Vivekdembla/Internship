package com.food.internship

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.core.content.ContextCompat

class profileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        ///////////////////////
        if (Build.VERSION.SDK_INT < 16) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this,R.color.black)
        }
        ///////////////////////

        val channelName = findViewById<TextView>(R.id.channelName)
        val Status = findViewById<TextView>(R.id.Status)
        val SubscriberCount = findViewById<TextView>(R.id.SubscriberCount)

        val ChannelName = intent.getStringExtra("ChannelName")
        val Subscribers = intent.getIntExtra("Subscribers",1000)
        val Subscribed = intent.getBooleanExtra("Subscribed",false)

        channelName.text = ChannelName
        if(Subscribed) {
        Status.text = "Subscribed"
        }
        else
            Status.text = "Not Subscribed"
        SubscriberCount.text = Subscribers.toString()
    }
}