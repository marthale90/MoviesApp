package com.test.moviesapp.domain

data class MovieDomainModel(
    val id: Int,
    val title: String,
    val posterPath: String,
    val genres: List<String> = listOf(),
    val overview: String,
    val releaseDate: String
)
