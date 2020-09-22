package com.example.week1.Service

import com.example.week1.veiwModel.Movie
import com.example.week1.veiwModel.MovieResponse
import com.example.week1.veiwModel.Trailer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        private var instance: ApiService? = null
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        private const val YOUR_API_KEY = "AIzaSyDwwdvNUHzfFt7Y6L-TA8I_7Cc8y4wKa9M"

       private fun create(): ApiService {

            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }

        fun getInstance(): ApiService {
            if (instance == null) {
                synchronized(ApiService::class.java) {
                    instance = create()
                }
            }
            return instance!!
        }
    }
    @GET("movie/now_playing")
    fun getPage(
    ): Call<MovieResponse>

    @GET("movie/now_playing")
    fun getMovieNowPlaying(
        @Query("api_key") apiKey: String = "a07e22bc18f5cb106bfe4cc1f83ad8ed",
        @Query("page") page: Int = 1
    ): Call<MovieResponse>

    @GET("movie/209112/trailers")
    fun getTrailers(
        @Query("api_key") apiKey: String = "a07e22bc18f5cb106bfe4cc1f83ad8ed"
    ) : Call<Trailer>

}


