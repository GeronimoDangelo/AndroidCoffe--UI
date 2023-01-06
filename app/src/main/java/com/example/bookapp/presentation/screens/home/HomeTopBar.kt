package com.example.bookapp.presentation.screens.home

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookapp.R
import com.example.bookapp.ui.theme.topBarBg
import com.example.bookapp.ui.theme.topBarTxt

@Composable
fun HomeTopBar(onSearchClicked: () -> Unit) {

    TopAppBar(
        title = {
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
                    contentDescription = stringResource(R.string.search_icon),
                    tint = MaterialTheme.colors.topBarTxt
                )
            }
        }
    )

}

@Preview
@Composable
fun Prev() {
    HomeTopBar { }
}