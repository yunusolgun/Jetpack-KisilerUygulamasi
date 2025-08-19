package com.robusttech.kisileruygulamasi

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.robusttech.kisileruygulamasi.entitity.Kisiler
import com.robusttech.kisileruygulamasi.ui.theme.Purple80

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun KisiDetaySayfa(gelenKisi: Kisiler) {
    val tfKisiAd = remember { mutableStateOf("") }
    val tfKisiTel = remember { mutableStateOf("") }
    val localFocusManager = LocalFocusManager.current

    LaunchedEffect(key1 = true) {
        tfKisiAd.value = gelenKisi.kisi_ad
        tfKisiTel.value = gelenKisi.kisi_tel
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("Kişi Detay")},
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
                        Log.e("TAG", "Kisi Detay Güncelle: ${gelenKisi.kisi_id} - $kisi_ad - $kisi_tel", )
                        localFocusManager.clearFocus()
                    },
                    modifier = Modifier.size(250.dp,50.dp)
                ) { Text("Güncelle") }

            }
        }

    )
}