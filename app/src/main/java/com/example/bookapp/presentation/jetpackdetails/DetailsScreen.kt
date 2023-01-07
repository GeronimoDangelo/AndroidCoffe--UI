package com.example.bookapp.presentation.jetpackdetails

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
fun DetailsScreen(
    navHostController: NavHostController,
    detailsViewModel: JetpackDetailsViewModel = hiltViewModel()
) {
    val selectedBook by detailsViewModel.selectedBook.collectAsState()
    val colorPalette by detailsViewModel.colorPalette

    if (colorPalette.isNotEmpty()) {
        JetpackDetailsContent(navHostController = navHostController, selectedJetpack = selectedBook, colors = colorPalette)

    } else {
        detailsViewModel.generateColorPalette()
    }


    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        detailsViewModel.uiEvent.collectLatest { event ->
            when (event) {
                is UiEvent.GenerateColorPalette -> {
                    val bitmap = convertImageUrlToBitMap(
                        imageUrl = "$BASE_URL${selectedBook?.image}",
                        context = context
                    )
                    if (bitmap != null) {
                        detailsViewModel.setColorPalette(
                            colors = extractColorsFromBitmap(bitmap = bitmap)
                        )
                    }
                }
            }
        }
    }

}