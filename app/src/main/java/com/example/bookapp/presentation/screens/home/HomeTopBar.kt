package com.example.bookapp.presentation.screens.home

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookapp.R
import com.example.bookapp.ui.theme.topBarBg
import com.example.bookapp.ui.theme.topBarTxt

@Composable
fun HomeTopBar(onSearchClicked: () -> Unit, onBackClicked: () -> Unit) {

    TopAppBar(
        title = {
            IconButton(onClick = onBackClicked ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.search_icon),
                    tint = MaterialTheme.colors.topBarTxt
                )
            }
            Text(
                text = "Explore",
                color = MaterialTheme.colors.topBarTxt
            )
        },
        backgroundColor = MaterialTheme.colors.topBarBg,
        actions = {
            IconButton(onClick = onSearchClicked) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "",
                    tint = MaterialTheme.colors.topBarTxt
                )
            }
        }
    )

}

@Preview
@Composable
fun Prev() {
    HomeTopBar(onBackClicked = {}, onSearchClicked = {})
}