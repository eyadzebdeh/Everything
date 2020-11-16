package com.eyad.everything.di.factory

import androidx.lifecycle.ViewModel
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.lang.RuntimeException
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class ViewModelFactoryImpl @Inject constructor(
    private val creators: MutableMap<Class<out ViewModel>, Provider<ViewModel>>): ViewModelFactory{

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var creator: Provider<out ViewModel>? = creators[modelClass]
        if (creator == null){
            for((key, value) in creators.entries){
                if (modelClass.isAssignableFrom(key)){
                    creator = value
                    break
                }
            }
        }
        if (creator == null){
            throw IllegalArgumentException("Unknown model class $modelClass")
        }
        try{
            return creator.get() as T
        }catch (e: Exception){
            throw RuntimeException(e)
        }
    }
}