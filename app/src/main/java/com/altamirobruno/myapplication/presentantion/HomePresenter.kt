package com.altamirobruno.myapplication.presentantion

import com.altamirobruno.myapplication.HomeFragment
import com.altamirobruno.myapplication.data.MovieRemoteDataSource
import com.altamirobruno.myapplication.model.Category
import com.altamirobruno.myapplication.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomePresenter(
    private val view: HomeFragment,
    private val dataSource: MovieRemoteDataSource = MovieRemoteDataSource(),
) {
    var categories = mutableListOf<Category>()

    fun loadingCategories() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                view.showProgress()
                val moviesPopular = dataSource.getMovies("movie/popular").getOrNull()
                val moviesTopRated = dataSource.getMovies("movie/top_rated").getOrNull()
                val moviesUpcoming = dataSource.getMovies("movie/upcoming").getOrNull()
                withContext(Dispatchers.Main) {
                    if (!moviesPopular.isNullOrEmpty())
                        categories.add(
                            Category(
                                id = 1,
                                name = "Em alta",
                                movies = inserCoverMovies(moviesPopular)
                            )
                        )
                    if (!moviesTopRated.isNullOrEmpty()) categories.add(
                        Category(
                            id = 2,
                            name = "Mais bem avaliados",
                            movies = inserCoverMovies(moviesTopRated)
                        )
                    )
                    if (!moviesUpcoming.isNullOrEmpty()) categories.add(
                        Category(
                            id = 3,
                            name = "Em breve",
                            movies = inserCoverMovies(moviesUpcoming)
                        )
                    )
                    view.showMovies()


                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    e.message?.let { view.showErrorToast(it.toString()) }

                }
            } finally {
                withContext(Dispatchers.Main) {
                    view.hideProgress()
                }
f
            }

        }


    }


    fun inserCoverMovies(moviesList: List<Movie>): List<Movie> {
        val posterUrl = "https://image.tmdb.org/t/p/original/"
        return moviesList.map { movie -> movie.copy(poster_path = "$posterUrl${movie.poster_path}") }

    }


}