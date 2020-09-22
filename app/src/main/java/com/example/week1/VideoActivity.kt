package com.example.week1

import android.os.Bundle
import android.util.Log
import com.example.week1.veiwModel.Youtube
import com.example.week1.module.IMovieDetailView
import com.example.week1.module.MovieDetailPresenter
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

class VideoActivity : YouTubeBaseActivity(), IMovieDetailView {

    private lateinit var trailers: ArrayList<Youtube>
    private lateinit var presenter : MovieDetailPresenter
    private lateinit var player : YouTubePlayerView
//    private lateinit var trailer:Trailer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        bindViews()
//        init(trailer.youtube)
        presenter = MovieDetailPresenter()
        presenter.attachView(this)
        presenter.getAllTrailer()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    private fun bindViews(){
        player = findViewById(R.id.player)
    }


    override fun onLoadSuccess(list: ArrayList<Youtube>?) {
        Log.d("tus", list.toString())
        trailers = list ?: arrayListOf()

        val source = trailers[0].source
        player.initialize("AIzaSyDwwdvNUHzfFt7Y6L-TA8I_7Cc8y4wKa9M",
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(provider: YouTubePlayer.Provider,
                                                     youTubePlayer: YouTubePlayer, b: Boolean) {
                    youTubePlayer.cueVideo(source)
                }

                override fun onInitializationFailure(provider: YouTubePlayer.Provider,
                                                     youTubeInitializationResult: YouTubeInitializationResult) {
                }
            })
    }

    override fun onError() {
        Log.d("tus", "error")
    }
}
