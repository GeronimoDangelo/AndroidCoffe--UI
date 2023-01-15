package com.coffee.bookapp.presentation.screens.jetpack

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.coffee.bookapp.R
import com.coffee.bookapp.ui.theme.topBarBg
import com.coffee.bookapp.ui.theme.topBarTxt

@Composable
fun JetpackTopBar(onSearchClicked: () -> Unit, onBackClick: () -> Unit) {
    
    TopAppBar(
        title = {
            IconButton(onClick = onBackClick ) {
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
            IconButton(onClick = onSearchClicked ) {
                Icon(imageVector = Icons.Default.Search , contentDescription = "" )
            }
        }
    )
}


@Preview
@Composable
fun PrevCofie() {
    JetpackTopBar(onSearchClicked = { /*TODO*/ }) {
        
    }
}