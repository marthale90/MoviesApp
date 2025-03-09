package com.test.moviesapp.domain

sealed class ErrorDomainModel {
    data class ApiError(val code: Int, val message: String) : ErrorDomainModel()
    data object NetworkError : ErrorDomainModel()
    data object UnknownError : ErrorDomainModel()
}
