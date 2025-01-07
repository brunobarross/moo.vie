package com.altamirobruno.myapplication.data


import co.tiagoaguiar.tutorial.jokerappdev.data.HTTPClient
import com.altamirobruno.myapplication.model.Movie
import com.altamirobruno.myapplication.model.MovieResponse
import com.altamirobruno.myapplication.model.TrailerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface TMDBApi {
    @GET()
    suspend fun getMovies(
        @Url url: String,
        @Query("query") query: String? = null,
        @Query("apiKey") apiKey: String = HTTPClient.API_KEY,
        @Query("language") language: String = HTTPClient.API_LANGUAGE,

    ): Response<MovieResponse>


    @GET("movie/{id}")
    suspend fun getMovie(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String = HTTPClient.API_KEY,
        @Query("language") language: String = HTTPClient.API_LANGUAGE
    ): Response<Movie>

    @GET("movie/{id}/videos")
    suspend fun getMovieTrailer(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String = HTTPClient.API_KEY,
        @Query("language") language: String = HTTPClient.API_LANGUAGE
    ): Response<TrailerResponse>


}