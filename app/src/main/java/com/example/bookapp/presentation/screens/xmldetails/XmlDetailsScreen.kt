package com.example.bookapp.presentation.screens.xmldetails

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.bookapp.util.Constants.BASE_URL
import com.example.bookapp.util.PaletteGenerator
import kotlinx.coroutines.flow.collectLatest

@ExperimentalMaterialApi
@Composable
fun XmlDetailsScreen(
    navHostController: NavHostController,
    xmlDetailsViewModel: XmlDetailsViewModel = hiltViewModel()
) {

    val selectedXml by xmlDetailsViewModel.selectedXml.collectAsState()
    val colorsPalette3 by xmlDetailsViewModel.colorsPalette3



    if (colorsPalette3.isNotEmpty()) {

        XmlDetailsContent(
            navHostController = navHostController,
            selectedXml = selectedXml,
            colorsXml = colorsPalette3
        )

    } else {
        xmlDetailsViewModel.generateColorsPalette3()
    }


    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        xmlDetailsViewModel.uiEvents3.collectLatest { events ->
            when (events) {
                is UiEvents3.GenerateColorsPalette -> {
                    val bitmaps = PaletteGenerator.convertImageUrlToBitMap(
                        imageUrl = "${BASE_URL}${selectedXml?.image}",
                        context = context
                    )
                    if (bitmaps != null) {
                        xmlDetailsViewModel.setColorsPalette3(
                            colors = PaletteGenerator.extractColorsFromBitmap(bitmap = bitmaps)
                        )
                    }
                }
            }
        }
    }

}