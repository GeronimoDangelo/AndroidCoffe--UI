package com.coffee.bookapp.presentation.screens.jetpack

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.coffee.bookapp.navigation.Screen
import com.coffee.bookapp.presentation.common.BannerAdView
import com.coffee.bookapp.presentation.common.JetpackList
import com.coffee.bookapp.ui.theme.topBarBg
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun JetpackScreen(navHostController: NavHostController, jetpackViewModel: JetpackViewModel = hiltViewModel()) {

    val jetpacks = jetpackViewModel.getAllJetpacks.collectAsLazyPagingItems()

    val systemUiController = rememberSystemUiController()
    val topBarColor = MaterialTheme.colors.topBarBg

    SideEffect {
        systemUiController.setStatusBarColor(color = topBarColor)
    }

    Scaffold(
        topBar = {
        JetpackTopBar(
            onSearchClicked = { navHostController.navigate(Screen.JetpackSearch.route) },
            onBackClick = { navHostController.popBackStack() }
        )

    }, content = {
            JetpackList(jetpacks = jetpacks , navHostController = navHostController )
            BannerAdView()

    })
}

