package com.example.week1.UI

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.week1.veiwModel.Movie
import com.example.week1.R

class MovieViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){

    private var movieName = itemView.findViewById<TextView>(R.id.txtMv)
    private var overView = itemView.findViewById<TextView>(R.id.txtOverView)
    private val imgPicture = itemView.findViewById<ImageView>(R.id.imgMv)

    fun bind (movie : Movie)
    {
        movieName.text = movie.title
        overView.text = movie.overview
        Glide.with(itemView)
            .load("https://image.tmdb.org/t/p/w500"+ "${movie.poster}")
            .fitCenter()
            .into(imgPicture)
    }
}
