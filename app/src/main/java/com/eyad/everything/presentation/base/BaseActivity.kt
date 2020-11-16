package com.eyad.everything.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eyad.everything.di.factory.ViewModelFactory
import dagger.android.AndroidInjection
import javax.inject.Inject

abstract class BaseActivity: AppCompatActivity(){

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        handleDependencyInjection()
        super.onCreate(savedInstanceState)
    }

    private fun handleDependencyInjection() {
        AndroidInjection.inject(this)
    }


}