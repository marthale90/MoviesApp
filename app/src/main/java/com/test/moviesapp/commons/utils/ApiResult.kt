package com.test.moviesapp.commons.utils

import com.test.moviesapp.domain.ErrorDomainModel

sealed class ApiResult<out T> {
    data class Loading(val isLoading: Boolean) : ApiResult<Nothing>()
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error(val error: ErrorDomainModel) : ApiResult<Nothing>()
}
