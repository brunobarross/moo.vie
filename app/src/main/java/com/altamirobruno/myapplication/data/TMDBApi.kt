package com.altamirobruno.myapplication.data


import co.tiagoaguiar.tutorial.jokerappdev.data.HTTPClient
import com.altamirobruno.myapplication.model.Movie
import com.altamirobruno.myapplication.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBApi {
    @GET("movie/popular")
    fun getMovies(@Query("apiKey") apiKey: String = HTTPClient.API_KEY): Call<MovieResponse>


}