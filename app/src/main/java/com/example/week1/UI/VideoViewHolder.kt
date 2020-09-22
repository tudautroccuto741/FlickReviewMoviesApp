package com.example.week1.UI

import android.content.Context
import android.content.res.Configuration
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.week1.veiwModel.Movie
import com.example.week1.R

class VideoViewHolder(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView) {

    private var videoName = itemView.findViewById<TextView>(R.id.tv_title_video)
    private var overViewVideo = itemView.findViewById<TextView>(R.id.tv_overview_video)
    private val imgPictureVideo = itemView.findViewById<ImageView>(R.id.iv_trailer)

    fun bind (item : Movie)
    {
        val orientation = context.getResources().getConfiguration().orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
        videoName.text = item.title
        overViewVideo.text = item.overview
        }
        Glide.with(itemView)
            .load("https://image.tmdb.org/t/p/w500"+ "${item.poster}")
            .into(imgPictureVideo)
    }
}
