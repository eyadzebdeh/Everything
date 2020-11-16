package com.eyad.everything.di.module

import com.eyad.everything.presentation.main.MainActivity
import com.eyad.everything.presentation.main.WrongKeyActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesBuilderModule{

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindWrongKeyActivity(): WrongKeyActivity

}