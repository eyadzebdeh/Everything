package com.eyad.everything.presentation.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.eyad.everything.di.factory.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment : Fragment(){

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        handleDependencyInjection()
        super.onCreate(savedInstanceState)
    }

    private fun handleDependencyInjection() {
        AndroidSupportInjection.inject(this)
    }


}