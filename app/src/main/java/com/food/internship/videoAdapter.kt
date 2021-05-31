package com.food.internship

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.MediaController
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView

class videoAdapter:RecyclerView.Adapter<videoAdapter.VideoViewHolder>() {
    private val allUri = ArrayList<details>()
    class VideoViewHolder(itemView:View) :RecyclerView.ViewHolder(itemView){
        val videoView = itemView.findViewById<VideoView>(R.id.videoView)
        val play = itemView.findViewById<ImageView>(R.id.playImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val viewHolder=VideoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.videolist,parent,false))
        viewHolder.videoView.setOnClickListener {
            if(viewHolder.videoView.isPlaying) {
                viewHolder.videoView.pause()
                viewHolder.play.visibility = View.VISIBLE
            }
            else {
                viewHolder.videoView.start()
                viewHolder.play.visibility = View.GONE
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.videoView.setVideoURI(allUri[position].uri)
        holder.videoView.pause()


        holder.videoView.setOnPreparedListener(MediaPlayer.OnPreparedListener {
//            holder.play.visibility = View.GONE
//            it.start()
            val videoRatio = it.videoWidth.toFloat() / it.videoHeight.toFloat()
            val screenRatio = holder.videoView.width.toFloat() / holder.videoView.height.toFloat()
            val scale = videoRatio / screenRatio
            if (scale >= 1f) {
                holder.videoView.scaleX = scale
            } else {
                holder.videoView.scaleY = scale
            }

        })
        holder.videoView.setOnCompletionListener {
            it.start()
        }
        holder.play.visibility = View.VISIBLE
    }

    override fun getItemCount(): Int {
        return allUri.size
    }

    fun updateList(urilist : List<details>){
        allUri.clear()
        allUri.addAll(urilist)
        notifyDataSetChanged()
    }
}