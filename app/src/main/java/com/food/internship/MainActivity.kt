package com.food.internship

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity(){
    lateinit var recyclerView: RecyclerView
    lateinit var adapter:videoAdapter
    var list : ArrayList<details> =  ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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

        recyclerView = findViewById(R.id.recyclerView)
        adapter = videoAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)



        var videoPath = "android.resource://"+ getPackageName()+ "/"+ R.raw.video1
        var uri = Uri.parse(videoPath)
        var x = details(uri,"CodeHelp",1000000,false)
        list.add(x)

        videoPath = "android.resource://"+ getPackageName()+ "/"+ R.raw.video2
        uri = Uri.parse(videoPath)
        x = details(uri,"ProxyNotes",123,true)
        list.add(x)

        videoPath = "android.resource://"+ getPackageName()+ "/"+ R.raw.video3
        uri = Uri.parse(videoPath)
        x = details(uri,"Code With Harry",3213,false)
        list.add(x)

        videoPath = "android.resource://"+ getPackageName()+ "/"+ R.raw.video4
        uri = Uri.parse(videoPath)
        x = details(uri,"Harry Mack",123213,false)
        list.add(x)

        videoPath = "android.resource://"+ getPackageName()+ "/"+ R.raw.video5
        uri = Uri.parse(videoPath)
        x = details(uri,"Samay Raina",12321,false)
        list.add(x)
        adapter.updateList(list)



        //Creating MediaController

        //Creating MediaController
//        val mediaController = MediaController(this)
//        mediaController.setAnchorView(videoView)
//        val videoPath = "android.resource://"+ getPackageName()+ "/"+ R.raw.bodyparts
//        val uri = Uri.parse(videoPath)
//        videoView.setVideoURI(uri)
//        videoView.setMediaController(mediaController)
//        mediaController.setAnchorView(videoView)

        //specify the location of media file

        //specify the location of media file
//        val uri: Uri = Uri.parse(Environment.getExternalStorageDirectory().path + "/media/1.mp4")

        //Setting MediaController and URI, then starting the videoView

        //Setting MediaController and URI, then starting the videoView
//        videoView.setMediaController(mediaController)
//        videoView.setVideoURI(uri)
//        videoView.requestFocus()
//        videoView.start()




        //For Left Swipe
        val itemTouchHelperCallback: ItemTouchHelper.SimpleCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    // Row is swiped from recycler view

                    //////////////////////////////
                    val intent = Intent(this@MainActivity,profileActivity::class.java)
                    intent.putExtra("ChannelName",list[viewHolder.adapterPosition].channelname)
                    intent.putExtra("Subscribers",list[viewHolder.adapterPosition].Subscribers)
                    intent.putExtra("Subscribed",list[viewHolder.adapterPosition].subscribed)
                    startActivity(intent)
                    //////////////////////////////
                }
            }
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView)

        //For Right Swipe
        //////////////////////////
        val itemTouchHelperCallback2: ItemTouchHelper.SimpleCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    // Row is swiped from recycler view
                    // remove it from adapter
                    list[viewHolder.adapterPosition].subscribed = true
                    adapter.updateList(list)
                    Toast.makeText(this@MainActivity,"Subscribed",Toast.LENGTH_LONG).show()
                }
            }
        //////////////////////////

        ItemTouchHelper(itemTouchHelperCallback2).attachToRecyclerView(recyclerView)

    }
}