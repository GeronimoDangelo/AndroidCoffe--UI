package com.example.bookapp.presentation.screens.home

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.bookapp.navigation.Screen
import com.example.bookapp.presentation.common.ListContent
import com.example.bookapp.ui.theme.topBarBg
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val allBooks = homeViewModel.getAllBooks.collectAsLazyPagingItems()

    val systemUiController = rememberSystemUiController()
    val sys = MaterialTheme.colors.topBarBg

    SideEffect {
        systemUiController.setStatusBarColor(color = sys)
    }


    Scaffold(
        topBar = {
            HomeTopBar(onSearchClicked = { navHostController.navigate(Screen.Search.route) },
                onBackClicked = { navHostController.popBackStack() })
        },
        content = {
            ListContent(books = allBooks, navHostController = navHostController)
        }
    )
}