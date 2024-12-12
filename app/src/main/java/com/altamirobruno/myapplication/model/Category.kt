package com.altamirobruno.myapplication.model

data class Category(
    val id: Int,
    val name: String,
    var movies: List<Movie>
)
