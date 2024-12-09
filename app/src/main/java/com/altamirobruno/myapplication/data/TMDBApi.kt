package com.altamirobruno.myapplication.data


import co.tiagoaguiar.tutorial.jokerappdev.data.HTTPClient
import com.altamirobruno.myapplication.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBApi {
    @GET("discover/movie")
    fun getMovies(@Query("apiKey") apiKey: String = HTTPClient.API_KEY): Call<List<Movie>>
}