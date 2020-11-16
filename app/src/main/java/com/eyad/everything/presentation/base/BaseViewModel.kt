package com.eyad.everything.presentation.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    var progressLiveData: SingleLiveData<Boolean>? = SingleLiveData()
        private set

    var failureLiveData: SingleLiveData<Throwable>? = SingleLiveData()
        private set

    fun showProgress() {
        progressLiveData?.value = true
    }

    fun hideProgress() {
        progressLiveData?.value = false
    }

    fun showFailure(throwable: Throwable) {
        hideProgress()
        failureLiveData?.value = throwable
    }

    override fun onCleared() {
        super.onCleared()
    }
}