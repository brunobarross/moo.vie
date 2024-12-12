package com.altamirobruno.myapplication.presentantion

import android.util.Log
import com.altamirobruno.myapplication.HomeFragment
import com.altamirobruno.myapplication.data.ListMoviesCallback
import com.altamirobruno.myapplication.data.MovieRemoteDataSource
import com.altamirobruno.myapplication.model.Category
import com.altamirobruno.myapplication.model.Movie
import com.altamirobruno.myapplication.model.MovieResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomePresenter(
    private val view: HomeFragment,
    private val dataSource: MovieRemoteDataSource = MovieRemoteDataSource(),
) : ListMoviesCallback {
    var categories = mutableListOf<Category>()

    suspend fun findMovies() {

    }


    fun loadingCategries() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val moviesPopular = dataSource.getMovies("movie/popular").getOrNull()
                val moviesTopRated = dataSource.getMovies("movie/top_rated").getOrNull()
                withContext(Dispatchers.Main) {
                    if (!moviesPopular.isNullOrEmpty()) {
                        categories.add(
                            Category(
                                id = 1,
                                name = "Em alta",
                                movies = inserCoverInMovie(moviesPopular)
                            )
                        )

                    }
                    if (!moviesTopRated.isNullOrEmpty()) {
                        categories.add(
                            Category(
                                id = 2,
                                name = "Mais bem avaliados",
                                movies = inserCoverInMovie(moviesTopRated)
                            )
                        )

                    }
                    view.showMovies()


                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    e.message?.let { onError(it) }
                }
            }

        }


    }

    fun inserCoverInMovie(moviesList: List<Movie>): List<Movie> {
        val posterUrl = "https://image.tmdb.org/t/p/original/"
        return moviesList.map { movie -> movie.copy(poster_path = "$posterUrl${movie.poster_path}") }

    }


    override fun onSuccess(response: MovieResponse) {
        TODO("Not yet implemented")
    }

    override fun onError(response: String) {
        Log.d("Erro", response.toString())
    }


    override fun onComplete() {
        Log.d("Completou", "Finalizado")
    }

}