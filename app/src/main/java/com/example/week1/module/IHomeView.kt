package com.example.week1.module

import com.example.week1.veiwModel.Movie

interface IHomeView{
    fun onLoadSuccess(list: ArrayList<Movie>?, page : Int)
    fun onError()
}