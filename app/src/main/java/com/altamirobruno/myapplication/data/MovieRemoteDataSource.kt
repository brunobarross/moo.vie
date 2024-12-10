package com.altamirobruno.myapplication.data

import co.tiagoaguiar.tutorial.jokerappdev.data.HTTPClient
import com.altamirobruno.myapplication.model.Movie
import com.altamirobruno.myapplication.model.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRemoteDataSource {
    fun getMovies(callback: ListMoviesCallback) {
        HTTPClient.retrofit().create(TMDBApi::class.java)
            .getMovies()
            .enqueue(object : Callback<MovieResponse> {
                override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                    if (response.isSuccessful) {
                        val movies = response.body()
                        callback.onSuccess((movies ?: emptyList<MovieResponse>()) as MovieResponse)
                    } else {
                        val error = response.errorBody()?.toString()
                        callback.onError(error ?: "Erro desconhecido")
                    }
                    callback.onComplete()
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    callback.onError(t.message ?: "Erro interno")
                }
            })

    }
}