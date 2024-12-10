package com.altamirobruno.myapplication.data

import com.altamirobruno.myapplication.model.MovieResponse

interface ListMoviesCallback {
    fun onSuccess(response: MovieResponse)
    fun onError(response: String)
    fun onComplete()
}