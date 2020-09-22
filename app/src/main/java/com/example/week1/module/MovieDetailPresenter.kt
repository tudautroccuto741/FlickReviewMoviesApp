package com.example.week1.module

import android.util.Log
import com.example.basemvp.base.BasePresenter
import com.example.week1.Service.ApiService
import com.example.week1.veiwModel.Trailer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailPresenter : IMovieDetailPresenter, BasePresenter<IMovieDetailView>() {
    override fun getAllTrailer() {
        val apiService = ApiService.getInstance().getTrailers()
        apiService.enqueue(object : Callback<Trailer>{
            override fun onResponse(call: Call<Trailer>, response: Response<Trailer>) {
                if(isViewAttachted()) {
                    getView()?.onLoadSuccess(response.body()?.youtube)
                }
            }

            override fun onFailure(call: Call<Trailer>, t: Throwable) {
                Log.d("loi", t.message.toString())
                if(isViewAttachted()){
                    getView()?.onError()
                }
            }

        })
    }
}
