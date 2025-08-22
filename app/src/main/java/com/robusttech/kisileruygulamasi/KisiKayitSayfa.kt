package com.robusttech.kisileruygulamasi

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.robusttech.kisileruygulamasi.ui.theme.Purple80
import com.robusttech.kisileruygulamasi.ui.theme.PurpleGrey80
import com.robusttech.kisileruygulamasi.viewmodel.AnasayfaViewModel
import com.robusttech.kisileruygulamasi.viewmodel.KisiKayitSayfaViewModel
import com.robusttech.kisileruygulamasi.viewmodelfactory.AnasayfaViewModelFactory
import com.robusttech.kisileruygulamasi.viewmodelfactory.KisiKayitSayfaViewModelFactory


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun KisiKayitSayfa() {
    val tfKisiAd = remember { mutableStateOf("") }
    val tfKisiTel = remember { mutableStateOf("") }
    val localFocusManager = LocalFocusManager.current

    val context = LocalContext.current
    val viewModel: KisiKayitSayfaViewModel = viewModel(
        factory = KisiKayitSayfaViewModelFactory(context.applicationContext as Application)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("Kişi Kayıt")},
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Purple80)
            )
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = tfKisiAd.value,
                    onValueChange = { tfKisiAd.value = it },
                    label = { Text("Kişi Ad")}
                )

                TextField(
                    value = tfKisiTel.value,
                    onValueChange = { tfKisiTel.value = it },
                    label = { Text("Kişi Tel")}
                )

                Button(
                    onClick = {
                        val kisi_ad = tfKisiAd.value
                        val kisi_tel = tfKisiTel.value
                        viewModel.kayit(kisi_ad,kisi_tel)
                        localFocusManager.clearFocus()
                    },
                    modifier = Modifier.size(250.dp,50.dp)
                ) { Text("Kaydet") }

            }
        }

    )
}