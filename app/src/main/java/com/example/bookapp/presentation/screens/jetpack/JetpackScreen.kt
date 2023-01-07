package com.example.bookapp.presentation.screens.jetpack

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.bookapp.navigation.Screen
import com.example.bookapp.presentation.common.ListJetpack
import com.example.bookapp.ui.theme.topBarBg
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun JetpackScreen(
    navHostController: NavHostController,
    jetpackViewModel: JetpackViewModel = hiltViewModel()
) {

    val allJetpacks = jetpackViewModel.getAllJetpacks.collectAsLazyPagingItems()

    val systemUiController = rememberSystemUiController()
    val sys = MaterialTheme.colors.topBarBg

    SideEffect {
        systemUiController.setStatusBarColor(color = sys)
    }


    Scaffold(
        topBar = {
            JetpackTopBar(onSearchClicked = {navHostController.navigate(Screen.Search.route)}, onBackClicked = { navHostController.popBackStack()})
        },
        content = {
            ListJetpack(jetpacks = allJetpacks, navHostController = navHostController)
        }
    )
}