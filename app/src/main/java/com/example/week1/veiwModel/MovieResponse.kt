package com.example.week1.veiwModel

import com.google.gson.annotations.SerializedName

data class MovieResponse (
    @SerializedName("results")
    val results: ArrayList<Movie>?,
    @SerializedName("page")
    val page : Int?,
    @SerializedName("total_results")
    val total_results : Int?,
    @SerializedName("dates")
    val dates : Dates?,
    @SerializedName("total_pages")
    val total_page : Int?
)