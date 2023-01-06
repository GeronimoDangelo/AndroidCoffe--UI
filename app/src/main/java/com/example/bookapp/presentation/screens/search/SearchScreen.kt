package com.example.bookapp.presentation.screens.search

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.bookapp.presentation.common.ListContent
import com.example.bookapp.ui.theme.topBarBg
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SearchScreen(
    navHostController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {

    val searchQuery by searchViewModel.searchQuery
    val books = searchViewModel.searchedBooks.collectAsLazyPagingItems()

    val systemUiController = rememberSystemUiController()
    val ss = MaterialTheme.colors.topBarBg


    SideEffect {
        systemUiController.setStatusBarColor(color = ss)
    }

    Scaffold(
        topBar = {
            SearchTopBar(
                text = searchQuery,
                onTextChange = {
                    searchViewModel.updateSearchQuery(query = it)
                },
                onSearchClicked = {
                    searchViewModel.searchBooks(query = it)
                },
                onCloseClicked = {
                    navHostController.popBackStack()
                }
            )
        },
        content = {
            ListContent(books = books, navHostController = navHostController)
        }
    )
}