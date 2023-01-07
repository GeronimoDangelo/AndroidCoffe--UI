package com.example.bookapp.presentation.screens.jetpack

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.bookapp.navigation.Screen
import com.example.bookapp.presentation.common.JetpackList
import com.example.bookapp.ui.theme.topBarBg
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun JetpackScreen(navHostController: NavHostController, jetpackViewModel: JetpackViewModel = hiltViewModel()) {

    val jetpacks = jetpackViewModel.getAllJetpacks.collectAsLazyPagingItems()

    val systemUiController = rememberSystemUiController()
    val topBarColor = MaterialTheme.colors.topBarBg

    SideEffect {
        systemUiController.setStatusBarColor(color = topBarColor)
    }

    Scaffold(topBar = {
        JetpackTopBar(
            onSearchClicked = { navHostController.navigate(Screen.JetpackSearch.route) },
            onBackClick = { navHostController.popBackStack() }
        )

    }, content = {
            JetpackList(jetpacks = jetpacks , navHostController = navHostController )
    })


}