package com.altamirobruno.myapplication.presentantion

import android.util.Log
import com.altamirobruno.myapplication.HomeFragment
import com.altamirobruno.myapplication.data.ListMoviesCallback
import com.altamirobruno.myapplication.data.MovieRemoteDataSource
import com.altamirobruno.myapplication.model.MovieResponse


class HomePresenter(
    private val view: HomeFragment,
    private val dataSource: MovieRemoteDataSource = MovieRemoteDataSource()
) : ListMoviesCallback {

    fun findAllMovies() {
        dataSource.getMovies(this)
    }

    override fun onSuccess(response: MovieResponse) {
        val movies = response.results
        val posterUrl = "https://image.tmdb.org/t/p/original/"
        val moviesWithCover = movies.map { movie ->
            movie.copy(poster_path = "$posterUrl${movie.poster_path}")
        }
        view.showMovies(moviesWithCover)
    }

    override fun onError(response: String) {
        Log.d("Erro", response.toString())
    }

    override fun onComplete() {
        Log.d("Completou", "Finalizado")
    }

}