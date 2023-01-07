package com.example.bookapp.presentation.screens.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookapp.R
import com.example.bookapp.ui.theme.topBarBg
import com.example.bookapp.ui.theme.topBarTxt

@Composable
fun CategoryTopBar() {
    TopAppBar(
        title = {
            Image(painter = painterResource(id = R.drawable.logo), contentDescription ="" )
            Text(
                text = "Categories",
                color = MaterialTheme.colors.topBarTxt
            )
        },
        backgroundColor = MaterialTheme.colors.topBarBg,

    )
}


@Preview
@Composable
fun Rrr() {
    CategoryTopBar()
}