package com.test.moviesapp.usescases

import com.test.moviesapp.commons.Constants
import com.test.moviesapp.commons.utils.ApiResult
import com.test.moviesapp.data.remote.repository.Repository
import com.test.moviesapp.domain.MovieDomainModel

class GetPopularMoviesUseCase(
    private val repository: Repository,
    private val page: Int = 1,
    private val language: String = Constants.Remote.LANGUAGE_ES
) {

    suspend fun invoke(): ApiResult<List<MovieDomainModel>> =
        repository.getMovies(listBy = Constants.Remote.POPULAR, page = page, language = language)
}
