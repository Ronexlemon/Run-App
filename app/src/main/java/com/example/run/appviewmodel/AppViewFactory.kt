@file:OptIn(ExperimentalTime::class)

package com.example.run.appviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.run.apprepository.AppRepository
import kotlin.time.ExperimentalTime

class AppViewFactory(private val repo :AppRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AppViewModel::class.java)){
            return AppViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}