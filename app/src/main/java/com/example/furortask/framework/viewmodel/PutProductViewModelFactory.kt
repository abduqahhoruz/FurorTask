package com.example.furortask.framework.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.furortask.framework.repo.MainRepo

class PutProductViewModelFactory(val app: Application, private val repository: MainRepo) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PutProductFragmnetViewModel::class.java)) {
            return PutProductFragmnetViewModel(repository, app) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}