package com.test.moviesapp.data.remote.model

import com.test.moviesapp.commons.Constants
import com.test.moviesapp.domain.MovieDomainModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponseModel(
    val page: Int,
    val results: List<MovieModel>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
) {
    fun toDomain(): List<MovieDomainModel> = results.map {
        MovieDomainModel(
            id = it.id,
            title = it.title,
            posterPath = it.posterPath,
            overview = it.overview,
            releaseDate = it.releaseDate,
            genres = it.genreIds.map { genreId -> Constants.UI.GENRE_MOVIES[genreId] ?: "" }
        )
    }
}
