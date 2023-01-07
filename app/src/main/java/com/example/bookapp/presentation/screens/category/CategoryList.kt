package com.example.bookapp.presentation.screens.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bookapp.R
import com.example.bookapp.navigation.Screen
import com.example.bookapp.ui.theme.topBarTxt

@Composable
fun CategoryList(navHostController: NavHostController) {

    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        item {
            Box(
                modifier = Modifier
                    .height(400.dp)
                    .clickable {
                        navHostController.navigate(Screen.Home.route)
                    },
                contentAlignment = Alignment.BottomStart,
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(
                        8.dp
                    )
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(id = R.drawable.kotlin),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Box(
                modifier = Modifier
                    .height(400.dp)
                    .clickable {
                        navHostController.navigate(Screen.Home.route)
                    },
                contentAlignment = Alignment.BottomStart,
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(
                        8.dp
                    )
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(id = R.drawable.jetpack),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Box(
                modifier = Modifier
                    .height(400.dp)
                    .clickable {
                        navHostController.navigate(Screen.Home.route)
                    },
                contentAlignment = Alignment.BottomStart,
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(
                        8.dp
                    )
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(id = R.drawable.xml),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                }
            }

        }
    }


}