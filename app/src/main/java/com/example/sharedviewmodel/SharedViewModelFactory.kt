package com.example.sharedviewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


@Suppress("UNCHECKED_CAST")
class SharedViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

    companion object { // ÇOK ÖNEMLİ! -> BU SAYEDE tek bir viewmodel instance'ı oluyor her 2 activity içinde.
        @Volatile
        private var INSTANCE: SharedViewModel? = null

        fun getInstance(application: Application): SharedViewModel =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: SharedViewModel(application).also { INSTANCE = it }
            }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SharedViewModel::class.java)){
            return getInstance(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}