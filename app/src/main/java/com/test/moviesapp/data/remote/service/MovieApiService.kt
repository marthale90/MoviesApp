package com.test.moviesapp.data.remote.service

import com.test.moviesapp.data.remote.model.MoviesResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("{listBy}")
    suspend fun getMovies(
        @Path("listBy") listBy: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<MoviesResponseModel>
}
