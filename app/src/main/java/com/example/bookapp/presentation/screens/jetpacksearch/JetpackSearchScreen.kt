package com.example.bookapp.presentation.screens.jetpacksearch

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.bookapp.presentation.common.JetpackList
import com.example.bookapp.ui.theme.topBarBg
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun JetpackSearchScreen(
    navHostController: NavHostController,
    jetpackSearchJetpackViewModel: SearchJetpackViewModel = hiltViewModel()
) {

    val searchQueries by jetpackSearchJetpackViewModel.searchQueries
    val jetpacks = jetpackSearchJetpackViewModel.searchedJetpacks.collectAsLazyPagingItems()

    val systemUiControllers = rememberSystemUiController()
    val colorTopJetpack = MaterialTheme.colors.topBarBg

    SideEffect {
        systemUiControllers.setStatusBarColor(color = colorTopJetpack)
    }

    Scaffold(
        topBar = {
            JetpackSearchTopBar(
                texts = searchQueries,
                onTextChangeJetpack = { jetpackSearchJetpackViewModel.updateSearchQueries(query = it) },
                onSearchClickedJetpack ={ jetpackSearchJetpackViewModel.searchJetpacks(query = it) },
                onCloseClickedJetpack = { navHostController.popBackStack() }
            )
        },
        content = {
            JetpackList(jetpacks = jetpacks , navHostController = navHostController)
        }
    )
}