package com.flknlabs.mviexample.View

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.flknlabs.mviexample.Model.MainRepository
import com.flknlabs.mviexample.Model.Network.RestAPI

class MainViewModelFactory(private val restApi: RestAPI) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainActivityViewModel::class.java))
            return MainActivityViewModel(MainRepository(restApi)) as T

        throw IllegalArgumentException("Unknown class name")
    }
}