package com.eyad.everything.presentation.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.eyad.everything.databinding.ActivityMainBinding
import com.eyad.everything.di.factory.ViewModelFactory
import com.eyad.everything.presentation.base.BaseActivity

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

    }
}