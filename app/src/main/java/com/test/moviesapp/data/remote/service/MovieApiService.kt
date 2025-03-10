package com.test.moviesapp.data.remote.service

import com.test.moviesapp.data.remote.model.MoviesResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("discover/movie")
    suspend fun getMovies(
        @Query("sort_by") listBy: String,
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("include_adult") adultContent: Boolean = false,
        @Query("include_video") videos: Boolean = false
    ): Response<MoviesResponseModel>
}
