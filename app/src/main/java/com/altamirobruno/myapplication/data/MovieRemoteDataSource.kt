package com.altamirobruno.myapplication.data

import co.tiagoaguiar.tutorial.jokerappdev.data.HTTPClient
import com.altamirobruno.myapplication.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRemoteDataSource {
    //    fun getMovies(url: String, callback: ListMoviesCallback) {
//        HTTPClient.retrofit().create(TMDBApi::class.java)
//            .getMovies(url)
//            .enqueue(object : Callback<MovieResponse> {
//                override fun onResponse(
//                    call: Call<MovieResponse>,
//                    response: Response<MovieResponse>
//                ) {
//                    if (response.isSuccessful) {
//                        val movies = response.body()
//                        callback.onSuccess((movies ?: emptyList<MovieResponse>()) as MovieResponse)
//                    } else {
//                        val error = response.errorBody()?.toString()
//                        callback.onError(error ?: "Erro desconhecido")
//                    }
//                    callback.onComplete()
//                }
//
//                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
//                    callback.onError(t.message ?: "Erro interno")
//                }
//            })
//
//    }
    suspend fun getMovies(url: String): Result<List<Movie>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = HTTPClient.retrofit().create(TMDBApi::class.java).getMovies(url)
                if (response.isSuccessful) {
                    val movies = response.body()?.results ?: emptyList()
                    Result.success(movies)
                } else {
                    val error = response.errorBody()?.string() ?: "Erro desconhecido"
                    Result.failure(Exception(error))
                }
            } catch (e: Exception) {
                Result.failure(e)
            } as Result<List<Movie>>

        }

    }
}