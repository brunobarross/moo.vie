package com.altamirobruno.myapplication.model

data class MovieResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Long,
    val total_results: Long,
)
