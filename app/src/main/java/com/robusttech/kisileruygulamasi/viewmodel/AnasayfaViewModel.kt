package com.robusttech.kisileruygulamasi.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.robusttech.kisileruygulamasi.entitity.Kisiler
import com.robusttech.kisileruygulamasi.repo.KisilerDaoRepository

class AnasayfaViewModel: ViewModel() {
    var krepo = KisilerDaoRepository()
    var kisilerListesi = MutableLiveData<List<Kisiler>>()

    init {
        kisileriYukle()
        kisilerListesi = krepo.kisileriGetir()
    }

    fun kisileriYukle() {
        krepo.tumKisileriAl()
    }

    fun ara(aramaKelimesi: String) {
        krepo.kisiAra(aramaKelimesi)
    }

    fun sil(kisi_id: Int) {
        krepo.kisiSil(kisi_id)
    }

}