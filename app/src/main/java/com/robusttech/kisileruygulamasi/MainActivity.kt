package com.robusttech.kisileruygulamasi

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.robusttech.kisileruygulamasi.entitity.Kisiler
import com.robusttech.kisileruygulamasi.ui.theme.KisilerUygulamasiTheme
import com.robusttech.kisileruygulamasi.ui.theme.Purple80
import com.robusttech.kisileruygulamasi.ui.theme.PurpleGrey80

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KisilerUygulamasiTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
                SayfaGecisleri()
            }
        }
    }
}


@Composable
fun SayfaGecisleri() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "anasayfa") {
        composable("anasayfa") {
            Anasayfa(navController)
        }
        composable("kisi_kayit_sayfa") {
            KisiKayitSayfa()
        }
        composable("kisi_detay_sayfa/{kisi}", arguments = listOf(
            navArgument("kisi"){type = NavType.StringType}
        )) {
            val json = it.arguments?.getString("kisi")
            val nesne = Gson().fromJson(json,Kisiler::class.java)
            KisiDetaySayfa(nesne)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Anasayfa(navController: NavController) {
    val aramaYapiliyorMu = remember { mutableStateOf(false) }
    val tf = remember { mutableStateOf("") }
    val kisilerListesi = remember { mutableStateListOf<Kisiler>() }

    LaunchedEffect(key1 = true) {
        val k1 = Kisiler(1, "Ahmet", "11111")
        val k2 = Kisiler(2, "Zeynep", "22222")
        kisilerListesi.add(k1)
        kisilerListesi.add(k2)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (aramaYapiliyorMu.value) {
                        TextField(
                            value = tf.value,
                            onValueChange = {
                                tf.value = it
                                Log.e("TAG", "Kişi arama: $it")
                            },
                            label = { Text("Ara") },
                            colors = TextFieldDefaults.colors(
                                focusedIndicatorColor = Color.White,
                                unfocusedIndicatorColor = Color.Green,
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent

                            )
                        )
                    } else {
                        Text("Kişiler")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Purple80),
                actions = {
                    if (aramaYapiliyorMu.value) {
                        IconButton(
                            onClick = {
                                aramaYapiliyorMu.value = false
                                tf.value = ""
                            },
                            content = {
                                Icon(painter = painterResource(R.drawable.kapat_resim), "")
                            }
                        )
                    } else {
                        IconButton(
                            onClick = {
                                aramaYapiliyorMu.value = true
                            },
                            content = {
                                Icon(painter = painterResource(R.drawable.arama_resim), "")
                            }
                        )
                    }


                }
            )
        },
        content = {
            LazyColumn(modifier = Modifier.padding(it)) {
                items(
                    count = kisilerListesi.count(),
                    itemContent = {
                        val kisi = kisilerListesi[it]
                        Card(
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth()
                                .clickable {
                                    val kisiJson = Gson().toJson(kisi)
                                    navController.navigate("kisi_detay_sayfa/$kisiJson")
                                }
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("${kisi.kisi_ad} - ${kisi.kisi_tel}")
                                IconButton(onClick = {
                                    Log.e("TAG", "kişi sil: ${kisi.kisi_id}", )
                                }) {
                                    Icon(painter = painterResource(R.drawable.sil_resim), tint = Color.Red, contentDescription = "")
                                }

                            }
                        }
                    }
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("kisi_kayit_sayfa")
                },
                content = {
                    Icon(painter = painterResource(R.drawable.ekle_resim), "")
                },
                containerColor = PurpleGrey80
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KisilerUygulamasiTheme {
        // Anasayfa()
    }
}