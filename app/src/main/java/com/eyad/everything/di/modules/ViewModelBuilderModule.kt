package com.eyad.everything.di.modules

import androidx.lifecycle.ViewModel
import com.eyad.everything.di.annotations.ViewModelKey
import com.eyad.everything.di.factory.ViewModelFactory
import com.eyad.everything.di.factory.ViewModelFactoryImpl
import com.eyad.everything.presentation.main.MainViewModel
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
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel





}