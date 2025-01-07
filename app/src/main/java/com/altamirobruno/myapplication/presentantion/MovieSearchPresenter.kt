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

    fun loadingMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val moviesSearch = dataSource.getMovies("search/movie", "Jack+Reacher").getOrNull()

                withContext(Dispatchers.Main) {
                    if (!moviesSearch.isNullOrEmpty()) {
                        movies.addAll(inserCoverMovies(moviesSearch))
                        view.showMovies()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    e.message?.let { view.showErrorToast(it.toString()) }

                }
            }

        }


    }


    fun inserCoverMovies(moviesList: List<Movie>): List<Movie> {
        val posterUrl = "https://image.tmdb.org/t/p/original/"
        return moviesList.map { movie -> movie.copy(poster_path = "$posterUrl${movie.poster_path}") }

    }


}