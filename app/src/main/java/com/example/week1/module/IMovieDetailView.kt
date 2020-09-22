package com.example.week1.module

import com.example.week1.veiwModel.Youtube

interface IMovieDetailView {
    fun onLoadSuccess(list: ArrayList<Youtube>?)
    fun onError()
}