package com.eyad.everything.di.module

import com.eyad.everything.presentation.movieslist.MoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsBuilderModule {

    @ContributesAndroidInjector
    abstract fun bindMoviesFragment(): MoviesFragment

}