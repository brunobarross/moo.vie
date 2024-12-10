package com.altamirobruno.myapplication.presentantion

import android.util.Log
import com.altamirobruno.myapplication.HomeFragment
import com.altamirobruno.myapplication.data.ListMoviesCallback
import com.altamirobruno.myapplication.data.MovieRemoteDataSource
import com.altamirobruno.myapplication.model.Movie


class HomePresenter(
    private val view: HomeFragment,
    private val dataSource: MovieRemoteDataSource = MovieRemoteDataSource()
) : ListMoviesCallback {

    fun findAllMovies() {
        dataSource.getMovies(this)
    }

    override fun onSuccess(response: List<Movie>) {
        val movies = response
        view.showMovies(movies)
    }

    override fun onError(response: String) {
        Log.d("Erro", response.toString())
    }

    override fun onComplete() {
        Log.d("Completou", "Finalizado")
    }

}