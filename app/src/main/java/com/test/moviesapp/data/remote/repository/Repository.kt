package com.test.moviesapp.data.remote.repository

import com.test.moviesapp.commons.utils.ApiResult
import com.test.moviesapp.commons.utils.Logger
import com.test.moviesapp.data.remote.RetrofitClient
import com.test.moviesapp.data.remote.model.ErrorResponseModel
import com.test.moviesapp.domain.ErrorDomainModel
import com.test.moviesapp.domain.MovieDomainModel
import kotlinx.serialization.json.Json
import retrofit2.HttpException
import java.io.IOException

class Repository {
    private val _tag = Repository::class.java.simpleName
    private val moviesApi = RetrofitClient().moviesApi

    suspend fun getMovies(
        listBy: String,
        language: String,
        page: Int
    ): ApiResult<List<MovieDomainModel>> {
        return try {
            val response = moviesApi.getMovies(listBy, language, page)
            if (response.isSuccessful) {
                ApiResult.Success(response.body()?.toDomain() ?: emptyList())
            } else {
                ApiResult.Error(mapToDomainError(response.code(), response.errorBody()?.string()))
            }
        } catch (e: IOException) {
            Logger.log(tag = _tag, message = e.localizedMessage ?: "", throwable = e)
            ApiResult.Error(ErrorDomainModel.NetworkError)
        } catch (e: HttpException) {
            Logger.log(tag = _tag, message = e.localizedMessage ?: "", throwable = e)
            ApiResult.Error(mapToDomainError(e.code(), e.response()?.errorBody()?.string()))
        } catch (e: Exception) {
            Logger.log(tag = _tag, message = e.localizedMessage ?: "", throwable = e)
            ApiResult.Error(ErrorDomainModel.UnknownError)
        }
    }

    private fun mapToDomainError(code: Int, errorBody: String?): ErrorDomainModel {
        return try {
            val parsedError = errorBody?.let {
                Json { ignoreUnknownKeys = true }.decodeFromString(
                    ErrorResponseModel.serializer(),
                    it
                )
            }
            val message = parsedError?.statusMessage ?: "Unknown API error"
            Logger.log(tag = _tag, message = message)
            ErrorDomainModel.ApiError(code, message)
        } catch (e: Exception) {
            Logger.log(tag = _tag, message = e.localizedMessage ?: "", throwable = e)
            ErrorDomainModel.UnknownError
        }
    }
}
