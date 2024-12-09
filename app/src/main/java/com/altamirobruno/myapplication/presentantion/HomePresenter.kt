package com.altamirobruno.myapplication.presentantion

import android.util.Log
import com.altamirobruno.myapplication.data.ListMoviesCallback
import com.altamirobruno.myapplication.data.MovieRemoteDataSource
import com.altamirobruno.myapplication.model.Movie
import com.altamirobruno.myapplication.view.HomeFragment

class HomePresenter(
    private val view: HomeFragment,
    private val dataSource: MovieRemoteDataSource = MovieRemoteDataSource()
) : ListMoviesCallback {

    fun findAllMovies() {
        dataSource.getMovies(this)
    }

    override fun onSuccess(response: List<Movie>) {
        val movies = response.map { movie -> movie }
        view.showMovies(movies)
    }

    override fun onError(response: String) {
        Log.d("Erro", response.toString())
    }

    override fun onComplete() {
        Log.d("Completou", "Finalizado")
    }

}