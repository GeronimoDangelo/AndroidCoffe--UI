package com.example.bookapp.presentation.screens.xmldetails

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookapp.R
import com.example.bookapp.domain.model.XmlModel
import com.example.bookapp.presentation.components.InfoBox
import com.example.bookapp.presentation.components.OrderedList
import com.example.bookapp.presentation.screens.detailsjetpack.BottomSheetDesc
import com.example.bookapp.ui.theme.*
import com.example.bookapp.util.Constants
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@ExperimentalMaterialApi
@Composable
fun XmlDetailsContent(
    navHostController: NavHostController,
    selectedXml: XmlModel?,
    colorsXml: Map<String, String>
) {
    var vibrants3 by remember { mutableStateOf("#000000") }
    var darkVibrants3 by remember { mutableStateOf("#000000") }
    var onDarkVibrants3 by remember { mutableStateOf("#ffffff") }

    LaunchedEffect(key1 = selectedXml) {
        vibrants3 = colorsXml["vibrant"]!!
        darkVibrants3 = colorsXml["darkVibrant"]!!
        onDarkVibrants3 = colorsXml["onDarkVibrant"]!!
    }

    val systemUiControllerJetpack = rememberSystemUiController()

    SideEffect {
        systemUiControllerJetpack.setStatusBarColor(
            color = Color(
                android.graphics.Color.parseColor(
                    darkVibrants3
                )
            )
        )
    }

    val scaffoldStates = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    )

    val currentSheetFract3 = scaffoldStates.currentSheetFract3

    val radiusAnims by animateDpAsState(targetValue = if (currentSheetFract3 == 1f) 22.dp else 18.dp)

    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(topStart = radiusAnims, topEnd = radiusAnims),
        scaffoldState = scaffoldStates,
        sheetPeekHeight = MIN_SHEET_HEIGHT,
        sheetContent = {
            selectedXml?.let {
                BottomSheetDesc3(
                    selectedXml = it,
                    infoBoxIconColors = Color(android.graphics.Color.parseColor(vibrants3)),
                    sheetBackgroundColors = Color(android.graphics.Color.parseColor(darkVibrants3)),
                    contentColors = Color(android.graphics.Color.parseColor(onDarkVibrants3))
                )
            }
        },
        content = {
            selectedXml?.let { jets ->
                BackgroundContents3(
                    xmlImg = jets.image,
                    imgFraction = currentSheetFract3,
                    backgroundColors = Color(android.graphics.Color.parseColor(darkVibrants3)),
                    onCloseClick = {
                        navHostController.popBackStack()
                    }
                )
            }
        }
    )




}



@Composable
fun BottomSheetDesc3(
    selectedXml: XmlModel,
    infoBoxIconColors: Color = MaterialTheme.colors.buttonBackgroundColor,
    sheetBackgroundColors: Color = MaterialTheme.colors.welcomeScreenBackgroundColor,
    contentColors: Color = MaterialTheme.colors.titleColor
) {
    Column(
        modifier = Modifier
            .background(sheetBackgroundColors)
            .padding(all = LARGE_PADDING)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                modifier = Modifier
                    .size(35.dp),
                painter = painterResource(id = R.drawable.up),
                contentDescription = stringResource(
                    R.string.logo_icon_detailss
                ),
                tint = contentColors
            )
            Text(
                modifier = Modifier.padding(start = 15.dp),
                text = selectedXml.name,
                color = contentColors,
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 35.dp),
            horizontalArrangement = Arrangement.spacedBy(EXTRA_LARGE_PADDING)
        ) {
            InfoBox(
                icon = painterResource(id = R.drawable.level),
                iconColor = infoBoxIconColors,
                bigText = selectedXml.level,
                smallText = stringResource(R.string.month_details_placeholder),
                textColor = contentColors
            )
            InfoBox(
                icon = painterResource(id = R.drawable.time),
                iconColor = infoBoxIconColors,
                bigText = selectedXml.timeToLearn,
                smallText = stringResource(R.string.day_placeholder_details),
                textColor = contentColors
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            text = stringResource(R.string.about_detail),
            color = contentColors,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .padding(bottom = MEDIUM_PADDING),
            text = selectedXml.about,
            color = contentColors,
            fontSize = MaterialTheme.typography.body1.fontSize,
            maxLines = Constants.ABOUT_MAX_LINES_DETAILSCREEN
        )
        Spacer(modifier = Modifier.height(15.dp))
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start
        ) {
            OrderedList(
                title = stringResource(R.string.tags_placeholder_detail),
                items = selectedXml.tags,
                textColor = contentColors
            )
        }

    }
}

@Composable
fun BackgroundContents3(
    xmlImg: String,
    imgFraction: Float = 1f,
    backgroundColors: Color = MaterialTheme.colors.welcomeScreenBackgroundColor,
    onCloseClick: () -> Unit
) {

    val imageUrl = "${Constants.BASE_URL}${xmlImg}"


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColors)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = imgFraction + Constants.MINIMUNM_BACKGROUND_IMAGE_HEIGHT)
                .align(Alignment.TopStart),
            model = ImageRequest.Builder(LocalContext.current).data(data = imageUrl)
                .error(drawableResId = R.drawable.placeholder).build(),
            contentDescription = stringResource(R.string.book_image),
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End
        ) {
            IconButton(modifier = Modifier.padding(all = SMALL_PADDING),
                onClick = { onCloseClick() }) {
                Icon(
                    modifier = Modifier.size(ICONS_INFOBOX_SIZE),
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(R.string.close_icon),
                    tint = Color.White
                )
            }
        }
    }

}

@ExperimentalMaterialApi
val BottomSheetScaffoldState.currentSheetFract3: Float
    get() {
        val fraction = bottomSheetState.progress.fraction
        val targetValue = bottomSheetState.targetValue
        val currentValue = bottomSheetState.currentValue

        return when {
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Collapsed -> 1f
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Expanded -> 0f
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Expanded -> 1f - fraction
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Collapsed -> 0f + fraction
            else -> fraction
        }
    }