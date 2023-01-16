package com.coffee.bookapp.presentation.screens.category

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import com.coffee.bookapp.presentation.common.BannerAdView


@Composable
fun CategoryScreen(navHostController: NavHostController) {
    Scaffold(topBar = {
        CategoryTopBar()
    }, content = {
        CategoryList(navHostController = navHostController)
        BannerAdView()
    })

}

