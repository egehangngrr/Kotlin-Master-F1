package com.ozzy.kukaf1.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ozzy.kukaf1.service.Repository
import javax.inject.Inject

class ViewModelFactory @Inject
constructor(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PilotsViewModel(repository) as T
    }

}