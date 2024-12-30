package com.altamirobruno.myapplication.data

import co.tiagoaguiar.tutorial.jokerappdev.data.HTTPClient
import com.altamirobruno.myapplication.model.Movie
import com.altamirobruno.myapplication.model.Trailer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRemoteDataSource {

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
            }

        }

    }

    suspend fun getMovie(id: Int): Result<Movie> {
        return withContext(Dispatchers.IO) {
            try {
                val response = HTTPClient.retrofit().create(TMDBApi::class.java).getMovie(id)
                if (response.isSuccessful) {
                    val movie = response.body()
                    Result.success(movie)
                } else {
                    val error = response.errorBody()?.string() ?: "Erro desconhecido"
                    Result.failure(Exception(error))
                }
            } catch (e: Exception) {
                Result.failure(e)
            } as Result<Movie>

        }

    }

    suspend fun getMovieTrailer(id: Int): Result<List<Trailer>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = HTTPClient.retrofit().create(TMDBApi::class.java).getMovieTrailer(id)
                if (response.isSuccessful) {
                    val trailer = response.body()?.results ?: emptyList()
                    Result.success(trailer)
                } else {
                    val error = response.errorBody()?.string() ?: "Erro desconhecido"
                    Result.failure(Exception(error))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }

        }

    }

}