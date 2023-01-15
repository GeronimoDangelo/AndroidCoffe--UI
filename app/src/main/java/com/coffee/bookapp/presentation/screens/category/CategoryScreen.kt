package com.coffee.bookapp.presentation.screens.category

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.coffee.bookapp.presentation.common.BannerAdView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun CategoryScreen(navHostController: NavHostController) {
    Scaffold(topBar = {
        CategoryTopBar()
    }, content = {
        CategoryList(navHostController = navHostController)
        BannerAdView()
    })

}




@Preview
@Composable
fun Preview2() {
    CategoryScreen(navHostController = rememberNavController())
}

