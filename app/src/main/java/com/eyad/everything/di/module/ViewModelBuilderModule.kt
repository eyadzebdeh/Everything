package com.eyad.everything.di.module

import androidx.lifecycle.ViewModel
import com.eyad.everything.di.annotation.ViewModelKey
import com.eyad.everything.di.factory.ViewModelFactory
import com.eyad.everything.di.factory.ViewModelFactoryImpl
import com.eyad.everything.presentation.main.MainViewModel
import com.eyad.everything.presentation.movieslist.MoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class ViewModelBuilderModule{

    @Binds
    @Singleton
    abstract fun bindViewModelFactory(factory: ViewModelFactoryImpl): ViewModelFactory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    abstract fun bindMoviesViewModel(moviesViewModel: MoviesViewModel): ViewModel





}