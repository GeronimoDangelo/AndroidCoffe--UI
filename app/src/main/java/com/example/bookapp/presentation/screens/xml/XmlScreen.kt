package com.example.bookapp.presentation.screens.xml

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.bookapp.navigation.Screen
import com.example.bookapp.presentation.common.XmlList
import com.example.bookapp.ui.theme.topBarBg
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun XmlScreen(navHostController: NavHostController, xmlViewModel: XmlViewModel = hiltViewModel()) {

    val xmls = xmlViewModel.getAllXmls.collectAsLazyPagingItems()

    val systemUiController = rememberSystemUiController()
    val topBarColor = MaterialTheme.colors.topBarBg

    SideEffect {
        systemUiController.setStatusBarColor(color = topBarColor)
    }

    Scaffold(
        topBar = {
            XmlTopbar(onSearchClicked = { navHostController.navigate(Screen.XmlSearch.route) },
                onBackClick = { navHostController.popBackStack() })
        },
        content = {
            XmlList(xmls = xmls, navHostController = navHostController)
        })
}