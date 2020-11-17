package com.eyad.everything.domain.usecase

import com.eyad.everything.domain.model.MoviesResponse
import com.eyad.everything.domain.repository.MovieRepository
import javax.inject.Inject

class MoviesUseCase @Inject constructor(private val movieRepository: MovieRepository){

    suspend fun execute(page: Int? = 1): MoviesResponse{
        return movieRepository.getMovies()
    }


}