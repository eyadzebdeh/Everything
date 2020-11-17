package com.eyad.everything.data.repository

import com.eyad.everything.data.network.EverythingApi
import com.eyad.everything.domain.model.MoviesResponse
import com.eyad.everything.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepositoryImpl (
    private val everythingApi: EverythingApi
): MovieRepository {

    override suspend fun getMovies(): MoviesResponse {
        return everythingApi.getMovies()
    }


}