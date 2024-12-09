package com.altamirobruno.myapplication.data

import com.altamirobruno.myapplication.model.Movie

interface ListMoviesCallback {
    fun onSuccess(response: List<Movie>)
    fun onError(response: String)
    fun onComplete()
}