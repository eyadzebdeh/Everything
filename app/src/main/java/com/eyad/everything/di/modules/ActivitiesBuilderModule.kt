package com.eyad.everything.di.modules

import com.eyad.everything.presentation.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesBuilderModule{

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

}