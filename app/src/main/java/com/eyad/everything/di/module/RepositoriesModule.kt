package com.eyad.everything.di.module

import com.eyad.everything.data.network.EverythingApi
import com.eyad.everything.data.repository.MovieRepositoryImpl
import com.eyad.everything.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoriesModule {

    @Provides
    @Singleton
    fun providesMovieRepository(everythingApi: EverythingApi): MovieRepository{
        return MovieRepositoryImpl(everythingApi)
    }


}