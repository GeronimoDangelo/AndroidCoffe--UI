package com.example.bookapp.presentation.screens.category

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun CategoryScreen(navHostController: NavHostController) {
    Scaffold(topBar = {
        CategoryTopBar()
    }, content = {
        CategoryList(navHostController = navHostController)
    })

}


@Preview
@Composable
fun Preview2() {
    CategoryScreen(navHostController = rememberNavController())
}

