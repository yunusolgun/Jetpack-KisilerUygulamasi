package com.robusttech.kisileruygulamasi.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.robusttech.kisileruygulamasi.repo.KisilerDaoRepository

class KisiDetaySayfaViewModel: ViewModel() {
    var krepo = KisilerDaoRepository()

    fun guncelle(kisi_id: Int, kisi_ad: String, kisi_tel: String) {
        krepo.kisiGuncelle(kisi_id,kisi_ad,kisi_tel)
    }

}