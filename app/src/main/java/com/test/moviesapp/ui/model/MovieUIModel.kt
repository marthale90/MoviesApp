package com.test.moviesapp.ui.model

import com.test.moviesapp.domain.MovieDomainModel

sealed class MovieUIModel {
    data object Loading : MovieUIModel()
    data class Movies(
        val movies: List<MovieDomainModel>
    ) : MovieUIModel()
    data class Error(val error: String) : MovieUIModel()
}
