package com.example.week1

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.transition.Slide
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.week1.UI.MovieAdapter
import com.example.week1.UI.OnItemMovieClickListener
import com.example.week1.module.HomePresenter
import com.example.week1.module.IHomeView
import com.example.week1.veiwModel.Movie
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IHomeView {

    private lateinit var adapterMv : MovieAdapter
    private lateinit var presenter : HomePresenter
    private lateinit var rcv : RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    var position=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindControls()
        bindEvents()
        presenter = HomePresenter()
        presenter.getAllMovie(1)
        presenter.attachView(this)
        adapterMv = MovieAdapter(clickListener = object : OnItemMovieClickListener {
            override fun onClickItem(movie: Movie) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("movie", movie)
                Log.d("ERROR", toString())
                startActivity(intent)
            }
        })
        layoutManager = LinearLayoutManager(this)
        rcv.layoutManager = layoutManager
        rcv.adapter = adapterMv
        var scrollListener = object : EndlessScrollListener(layoutManager, 1) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                presenter.getAllMovie(page)
            }
        }
        rcv.addOnScrollListener(scrollListener)

    }

    override fun onResume() {
        super.onResume()
        Log.d("tus", "onResume")

    }

    override fun onPause() {
        super.onPause()
        Log.d("tus", "onPaused")
    }

    override fun onStop() {
        super.onStop()
        Log.d("tus", "onStoped")
    }

    private fun bindControls(){
        rcv = findViewById(R.id.rcvMovie)
    }

    private fun bindEvents(){}

    override fun onLoadSuccess(list: ArrayList<Movie>?, page: Int) {
        when(page){
            0->{
                adapterMv.addList(list)
                adapterMv.notifyDataSetChanged()
            }
            else -> {
                adapterMv.updateSource(list)
                if(adapterMv.itemCount < position){
                    rcv.scrollToPosition(adapterMv.itemCount-1)
                }
                else{
                    if(position!=0) rcv.scrollToPosition(position)
                }
            }
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("POSITION", layoutManager.findFirstCompletelyVisibleItemPosition())
        super.onSaveInstanceState(outState);
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        position = savedInstanceState.getInt("POSITION")
        super.onRestoreInstanceState(savedInstanceState)
    }


    override fun onError() {}

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}