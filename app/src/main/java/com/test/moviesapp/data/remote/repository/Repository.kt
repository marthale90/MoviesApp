package com.test.moviesapp.data.remote.repository
import android.util.Log
import com.test.moviesapp.commons.utils.ApiResult
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
            Log.e(_tag, e.localizedMessage ?: "", e)
            ApiResult.Error(ErrorDomainModel.NetworkError)
        } catch (e: HttpException) {
            Log.e(_tag, e.localizedMessage ?: "", e)
            ApiResult.Error(mapToDomainError(e.code(), e.response()?.errorBody()?.string()))
        } catch (e: Exception) {
            Log.e(_tag, e.localizedMessage ?: "", e)
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
            Log.e(_tag, message)
            ErrorDomainModel.ApiError(code, message)
        } catch (e: Exception) {
            Log.e(_tag, e.localizedMessage ?: "", e)
            ErrorDomainModel.UnknownError
        }
    }
}
