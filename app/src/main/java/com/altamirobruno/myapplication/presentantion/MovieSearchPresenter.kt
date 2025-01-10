package com.altamirobruno.myapplication.presentantion

import com.altamirobruno.myapplication.data.MovieRemoteDataSource
import com.altamirobruno.myapplication.model.Movie
import com.altamirobruno.myapplication.view.MovieSearchFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MovieSearchPresenter(
    private val view: MovieSearchFragment,
    private val dataSource: MovieRemoteDataSource = MovieRemoteDataSource(),
) {

    var movies = mutableListOf<Movie>()

    fun loadingMovies(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                resetMovies()
                val moviesSearch = dataSource.getMovies("search/movie", query).getOrNull()
                withContext(Dispatchers.Main) {
                    view.showProgress()
                    if (!moviesSearch.isNullOrEmpty()) {
                        movies.addAll(inserCoverMovies(moviesSearch))
                        view.showMovies()
                    } else {
                        view.showNotFoundText(query)
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    e.message?.let { view.showErrorToast(it.toString()) }

                }
            } finally {
                withContext(Dispatchers.Main) {
                    view.hideProgress()
                }

            }

        }


    }


    fun inserCoverMovies(moviesList: List<Movie>): List<Movie> {
        val posterUrl = "https://image.tmdb.org/t/p/original/"
        return moviesList.map { movie -> movie.copy(poster_path = "$posterUrl${movie.poster_path}") }

    }

    fun resetMovies() {
        if (movies.isNotEmpty()) {
            movies.clear()
        }
    }


}