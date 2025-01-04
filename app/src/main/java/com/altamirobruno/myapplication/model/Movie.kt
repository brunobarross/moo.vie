package com.altamirobruno.myapplication.model

data class Movie(
    val id: Int,
    val title: String,
    val vote_average: Double,
    val poster_path: String,
    val backdrop_path: String?,
    val overview: String,

)
