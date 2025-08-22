package com.robusttech.kisileruygulamasi.repo

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.robusttech.kisileruygulamasi.entitity.Kisiler
import com.robusttech.kisileruygulamasi.room.Veritabani
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class KisilerDaoRepository(var application: Application) {
    var kisilerListesi = MutableLiveData<List<Kisiler>>()
    var vt: Veritabani

    init {
        vt = Veritabani.veritabaniErisim(application)!!
        kisilerListesi = MutableLiveData<List<Kisiler>>()
    }

    fun kisileriGetir():MutableLiveData<List<Kisiler>> {
        return kisilerListesi
    }

    fun tumKisileriAl() {
        val job: Job = CoroutineScope(Dispatchers.Main).launch {
            kisilerListesi.value = vt.kisilerDao().tumKisiler()
        }
    }

    fun kisiAra(aramaKelimesi: String) {
        val job: Job = CoroutineScope(Dispatchers.Main).launch {
            kisilerListesi.value = vt.kisilerDao().kisiArama(aramaKelimesi)
        }
    }

    fun kisiKayit(kisi_ad: String, kisi_tel: String) {
        val job: Job = CoroutineScope(Dispatchers.Main).launch {
            val yeniKisi = Kisiler(0, kisi_ad, kisi_tel)
            vt.kisilerDao().kisiEkle(yeniKisi)
        }
    }

    fun kisiGuncelle(kisi_id: Int, kisi_ad: String, kisi_tel: String) {
        val job: Job = CoroutineScope(Dispatchers.Main).launch {
            val guncellenenKisi = Kisiler(kisi_id, kisi_ad, kisi_tel)
            vt.kisilerDao().kisiGuncelle(guncellenenKisi)
        }
    }

    fun kisiSil(kisi_id: Int) {
        val job: Job = CoroutineScope(Dispatchers.Main).launch {
            val silinenKisi = Kisiler(kisi_id, "", "")
            vt.kisilerDao().kisiSil(silinenKisi)
            tumKisileriAl()
        }
    }

}