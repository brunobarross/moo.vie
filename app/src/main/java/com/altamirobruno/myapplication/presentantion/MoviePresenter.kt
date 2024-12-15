package com.altamirobruno.myapplication.presentantion

import android.util.Log
import com.altamirobruno.myapplication.data.MovieRemoteDataSource
import com.altamirobruno.myapplication.model.Movie
import com.altamirobruno.myapplication.view.MovieFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MoviePresenter(
    private val view: MovieFragment,
    private val dataSource: MovieRemoteDataSource = MovieRemoteDataSource(),
) {

    fun loadingMovie(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val movie = dataSource.getMovie(id).getOrNull()
                withContext(Dispatchers.Main) {
                    if (movie !== null) {
                        val movieWithCover = inserCoverMovies(movie)
                        view.showMovieDetail(movieWithCover)
                    }
                }


            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    e.message?.let { Log.d("Erro", it.toString()) }
                }
            }

        }


    }

    fun inserCoverMovies(movie: Movie): Movie {
        val posterUrl = "https://image.tmdb.org/t/p/original/"
        return movie.copy(poster_path = "$posterUrl${movie.backdrop_path}")


    }


}