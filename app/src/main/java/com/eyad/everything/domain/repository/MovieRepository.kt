package com.eyad.everything.domain.repository

import com.eyad.everything.domain.model.MoviesResponse

interface MovieRepository {

    suspend fun getMovies(): MoviesResponse

}