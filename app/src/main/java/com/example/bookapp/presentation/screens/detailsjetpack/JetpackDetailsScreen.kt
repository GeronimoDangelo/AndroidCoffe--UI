package com.example.bookapp.presentation.screens.detailsjetpack

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.bookapp.util.Constants.BASE_URL
import com.example.bookapp.util.PaletteGenerator.convertImageUrlToBitMap
import com.example.bookapp.util.PaletteGenerator.extractColorsFromBitmap
import kotlinx.coroutines.flow.collectLatest


@ExperimentalMaterialApi
@Composable
fun JetpackDetailsScreen(
    navHostController: NavHostController,
    jetpackDetailsViewModel: JetpackDetailsViewModel = hiltViewModel()
) {


    val selectedJetpack by jetpackDetailsViewModel.selectedJetpack.collectAsState()
    val colorsPalette by jetpackDetailsViewModel.colorsPalette

    if (colorsPalette.isNotEmpty()) {

        JetpackDetailsContent(
            navHostController = navHostController,
            selectedJetpack = selectedJetpack,
            colorsJetpack = colorsPalette
        )
    } else {
        jetpackDetailsViewModel.generateColorsPalette()
    }

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        jetpackDetailsViewModel.uiEvents.collectLatest { events ->
            when (events) {
                is UiEvents.GenerateColorsPalette -> {
                    val bitmaps = convertImageUrlToBitMap(
                        imageUrl = "$BASE_URL${selectedJetpack?.image}",
                        context = context
                    )
                    if (bitmaps != null) {
                        jetpackDetailsViewModel.setColorsPalette(
                            colors = extractColorsFromBitmap(bitmap = bitmaps)
                        )
                    }
                }
            }
        }
    }

}