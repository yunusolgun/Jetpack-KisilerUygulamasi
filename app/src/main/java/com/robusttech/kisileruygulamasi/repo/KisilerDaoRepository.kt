package com.robusttech.kisileruygulamasi.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.robusttech.kisileruygulamasi.entitity.Kisiler

class KisilerDaoRepository {
    var kisilerListesi = MutableLiveData<List<Kisiler>>()

    init {
        kisilerListesi = MutableLiveData<List<Kisiler>>()
    }

    fun kisileriGetir():MutableLiveData<List<Kisiler>> {
        return kisilerListesi
    }

    fun tumKisileriAl() {
        val liste = mutableListOf<Kisiler>()
        val k1 = Kisiler(1, "Ahmet", "11111")
        val k2 = Kisiler(2, "Zeynep", "22222")
        liste.add(k1)
        liste.add(k2)

        kisilerListesi.value = liste
    }

    fun kisiAra(aramaKelimesi: String) {
        Log.e("TAG", "kisiAra: $aramaKelimesi", )
    }

    fun kisiKayit(kisi_ad: String, kisi_tel: String) {
        Log.e("TAG", "Kisi kayit: $kisi_ad - $kisi_tel", )
    }

    fun kisiGuncelle(kisi_id: Int, kisi_ad: String, kisi_tel: String) {
        Log.e("TAG", "Kisi güncelle: $kisi_id - $kisi_ad - $kisi_tel", )
    }

    fun kisiSil(kisi_id: Int) {
        Log.e("TAG", "Kisi sil: $kisi_id" )
    }

}