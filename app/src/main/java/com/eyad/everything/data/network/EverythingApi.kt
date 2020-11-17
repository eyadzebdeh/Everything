package com.eyad.everything.data.network

import com.eyad.everything.domain.model.MoviesResponse


class EverythingApi constructor(
    private val service: EverythingService
) {

    suspend fun getMovies(): MoviesResponse {
        return service.getMovies()
    }


}