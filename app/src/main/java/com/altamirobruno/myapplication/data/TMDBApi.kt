package com.altamirobruno.myapplication.data


import co.tiagoaguiar.tutorial.jokerappdev.data.HTTPClient
import com.altamirobruno.myapplication.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface TMDBApi {
    @GET()
    suspend  fun getMovies(
        @Url url: String,
        @Query("apiKey") apiKey: String = HTTPClient.API_KEY
    ): Response<MovieResponse>


}