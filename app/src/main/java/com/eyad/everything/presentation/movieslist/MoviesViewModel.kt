package com.eyad.everything.presentation.movieslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.eyad.everything.domain.model.MoviesResponse
import com.eyad.everything.domain.usecase.MoviesUseCase
import com.eyad.everything.presentation.base.BaseViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val moviesUseCase: MoviesUseCase
) : BaseViewModel(){

    var moviesLiveData: MutableLiveData<MoviesResponse> = MutableLiveData()
    var emptyLiveData: MutableLiveData<Int> = MutableLiveData()

    fun fetchMovies(page: Int? = 1) {
        viewModelScope.launch{
            try {
                val moviesResponse = moviesUseCase.execute(page)
                if (moviesResponse.movies?.size ?: 0 > 0){
                    moviesLiveData.value = moviesResponse
                }else{
                    emptyLiveData.value = page
                }
            }catch (e: Exception){
                failureLiveData?.value = e
            }
        }
    }
}