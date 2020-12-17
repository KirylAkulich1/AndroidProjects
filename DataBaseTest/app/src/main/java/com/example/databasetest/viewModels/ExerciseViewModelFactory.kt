package com.example.databasetest.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

class ExerciseViewModelFactory (val application: Application): ViewModelProvider.NewInstanceFactory(){
    override fun <T: ViewModel> create(modelClass:Class<T>): T {
        return MainViewModel(application) as T
    }
}