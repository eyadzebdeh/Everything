package com.eyad.everything.data.network

import com.eyad.everything.domain.model.MoviesResponse
import com.eyad.networking.client.annotation.ApiKey
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

@JvmSuppressWildcards
interface EverythingService {

    @ApiKey
    @GET("movie/popular")
    suspend fun getMovies(): MoviesResponse
}