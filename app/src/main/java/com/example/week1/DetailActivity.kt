package com.example.week1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.week1.veiwModel.Movie
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var tvTitle : TextView
    private lateinit var ib : ImageButton
    private var movie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        bindViews()
        init()
        ib.setOnClickListener(View.OnClickListener{
            val intent = Intent(this@DetailActivity, VideoActivity::class.java)
            startActivity(intent)
        })

    }

    private fun bindViews(){
        tvTitle = findViewById(R.id.txtTitle)
        ib = findViewById(R.id.ib_trailer)
    }

    private fun init(){
        val bundle = intent.extras
        movie = bundle?.getParcelable("movie")
        tvTitle.text = movie?.title
        txt_overview.text = movie?.overview
        txtDate.text = movie?.release_date
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500"+ "${movie?.poster}")
            .fitCenter()
            .into(ib_trailer)

        detail_rating_bar.numStars = movie?.vote_average!!.toInt()
    }

}