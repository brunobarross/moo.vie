package com.altamirobruno.myapplication.presentantion

import com.altamirobruno.myapplication.contract.Presenter
import com.altamirobruno.myapplication.data.MovieRemoteDataSource
import com.altamirobruno.myapplication.model.Movie
import com.altamirobruno.myapplication.model.Trailer
import com.altamirobruno.myapplication.view.MovieFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MoviePresenter(
    private val view: MovieFragment,
    private val dataSource: MovieRemoteDataSource = MovieRemoteDataSource(),

    ) : Presenter {

    private lateinit var trailer: Trailer
    fun loadingMovie(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val movie = dataSource.getMovie(id).getOrNull()
                withContext(Dispatchers.Main) {
                    view.showProgress()
                    if (movie !== null) {
                        val movieWithCover = inserCoverMovie(movie)
                        view.showMovieDetail(movieWithCover)

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

    fun loadingTrailer(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val trailerResponse = dataSource.getMovieTrailer(id).getOrNull()
                withContext(Dispatchers.Main) {
                    if (trailerResponse !== null) {
                        if (trailerResponse.isEmpty()) {
                            view.hidePlayButton()
                            return@withContext
                        }
                        trailer = trailerResponse[0]
                    }
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    e.message?.let { view.showErrorToast(it.toString()) }
                }
            }

        }


    }


    fun inserCoverMovie(movie: Movie): Movie {
        val posterUrl = "https://image.tmdb.org/t/p/original/"
        return movie.copy(poster_path = "$posterUrl${movie.backdrop_path}")


    }

    override fun onButtonClicked() {
        view.showTrailer(trailer)
    }


}