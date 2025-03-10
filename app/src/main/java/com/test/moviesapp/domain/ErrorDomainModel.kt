package com.test.moviesapp.domain

import android.content.Context
import com.test.moviesapp.R

sealed class ErrorDomainModel {
    data class ApiError(val code: Int, val message: String) : ErrorDomainModel()
    data object NetworkError : ErrorDomainModel()
    data object UnknownError : ErrorDomainModel()

    fun getMessage(context: Context): String {
        return when (this) {
            is NetworkError -> context.getString(R.string.error_network)
            is UnknownError -> String.format(
                context.getString(R.string.error_generic),
                "UNKNOWN",
                context.getString(R.string.error_generic_message)
            )
            is ApiError -> {
                when (code) {
                    // Take from https://developer.themoviedb.org/docs/errors
                    429 -> String.format(
                        context.getString(R.string.error_generic),
                        code.toString(),
                        context.getString(R.string.error_request_count)
                    )
                    else -> String.format(
                        context.getString(R.string.error_generic),
                        code.toString(),
                        context.getString(R.string.error_generic_message)
                    )
                }
            }
        }
    }
}
