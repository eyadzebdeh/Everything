package com.eyad.everything.di

import android.content.Context
import com.eyad.everything.di.modules.ActivitiesBuilderModule
import com.eyad.everything.di.modules.ViewModelBuilderModule
import com.eyad.everything.presentation.base.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ViewModelBuilderModule::class,
    ActivitiesBuilderModule::class,
])
interface ApplicationComponent: AndroidInjector<BaseApplication> {

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }

}