package com.coffee.bookapp.presentation.screens.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.coffee.bookapp.R
import com.coffee.bookapp.navigation.Screen

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
                        navHostController.navigate(Screen.Jetpack.route)
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
                        navHostController.navigate(Screen.XmlScreen.route)
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