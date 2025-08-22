package com.robusttech.kisileruygulamasi.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.robusttech.kisileruygulamasi.viewmodel.AnasayfaViewModel

class AnasayfaViewModelFactory(var application: Application): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AnasayfaViewModel(application) as T
    }
}