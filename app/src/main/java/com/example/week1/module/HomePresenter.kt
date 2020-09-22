package com.example.week1.module

import android.util.Log
import com.example.basemvp.base.BasePresenter
import com.example.week1.Service.ApiService
import com.example.week1.veiwModel.MovieResponse
import com.google.android.youtube.player.internal.t
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePresenter : IHomePresenter, BasePresenter<IHomeView>() {

    override fun getAllMovie(page : Int) {
        val apiService = ApiService.getInstance().getMovieNowPlaying(page = page)
        apiService.enqueue(object : Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d("TU", t.message.toString())
                if(isViewAttachted()){
                    getView()?.onError()
                }
            }
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if(isViewAttachted()) {
                    getView()?.onLoadSuccess(response.body()?.results, page)
                }
            }
        })
    }


}