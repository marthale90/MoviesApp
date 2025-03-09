package com.test.moviesapp.data.remote

import com.test.moviesapp.EnvironmentConstants
import com.test.moviesapp.data.remote.service.MovieApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create

class RetrofitClient {
    companion object {
        val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
            encodeDefaults = false
        }
    }

    private fun buildRetrofit(baseUrl: String, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    val moviesApi: MovieApiService by lazy {
        val okHttpClient = OkHttpClient.Builder().apply {
            addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer ${EnvironmentConstants.API_TOKEN}")
                    .build()
                chain.proceed(request)
            }
        }.build()
        buildRetrofit(EnvironmentConstants.BASE_URL, okHttpClient).create()
    }
}
