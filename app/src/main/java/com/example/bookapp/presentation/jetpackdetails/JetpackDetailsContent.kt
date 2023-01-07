package com.example.bookapp.presentation.jetpackdetails

import android.graphics.Color.parseColor
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookapp.domain.model.Book
import com.example.bookapp.ui.theme.*
import com.example.bookapp.R
import com.example.bookapp.domain.model.Jetpack
import com.example.bookapp.presentation.components.InfoBox
import com.example.bookapp.presentation.components.OrderedList
import com.example.bookapp.util.Constants.ABOUT_MAX_LINES_DETAILSCREEN
import com.example.bookapp.util.Constants.BASE_URL
import com.example.bookapp.util.Constants.MINIMUNM_BACKGROUND_IMAGE_HEIGHT
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@ExperimentalMaterialApi
@Composable
fun JetpackDetailsContent(
    navHostController: NavHostController, selectedJetpack: Jetpack?, colors: Map<String, String>
) {
    var vibrant by remember { mutableStateOf("#000000") }
    var darkVibrant by remember { mutableStateOf("#000000") }
    var onDarkVibrant by remember { mutableStateOf("#ffffff") }

    LaunchedEffect(key1 = selectedJetpack) {
        vibrant = colors["vibrant"]!!
        darkVibrant = colors["darkVibrant"]!!
        onDarkVibrant = colors["onDarkVibrant"]!!

    }
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(color = Color(parseColor(darkVibrant)))
    }


    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    )


    val currentSheetFraction = scaffoldState.currentSheetFraction


    val radiusAnim by animateDpAsState(
        targetValue =
        if (currentSheetFraction == 1f) 22.dp else 20.dp
    )


    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(topStart = radiusAnim, topEnd = radiusAnim),
        scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_SHEET_HEIGHT,
        sheetContent = {
            selectedJetpack?.let {
                BottomSheetContent(
                    selectedJetpack = it,
                    infoBoxIconColor = Color(parseColor(vibrant)),
                    sheetBackgroundColor = Color(parseColor(darkVibrant)),
                    contentColor = Color(parseColor(onDarkVibrant))
                )
            }
        },
        content = {
            selectedJetpack?.let { book ->
                BackgroundContent(bookImg = book.image,
                    imageFraction = currentSheetFraction,
                    backgroundColor = Color(parseColor(darkVibrant)),
                    onCloseClicked = {
                        navHostController.popBackStack()
                    })
            }
        })


}

@Composable
fun BottomSheetContent(
    selectedJetpack: Jetpack,
    infoBoxIconColor: Color = MaterialTheme.colors.buttonBackgroundColor,
    sheetBackgroundColor: Color = MaterialTheme.colors.welcomeScreenBackgroundColor,
    contentColor: Color = MaterialTheme.colors.titleColor
) {
    Column(
        modifier = Modifier
            .background(sheetBackgroundColor)
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
                tint = contentColor
            )
            Text(
                modifier = Modifier.padding(start = 15.dp),
                text = selectedJetpack.name,
                color = contentColor,
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
                iconColor = infoBoxIconColor,
                bigText = selectedJetpack.level,
                smallText = stringResource(R.string.month_details_placeholder),
                textColor = contentColor
            )
            InfoBox(
                icon = painterResource(id = R.drawable.time),
                iconColor = infoBoxIconColor,
                bigText = selectedJetpack.timeToLearn,
                smallText = stringResource(R.string.day_placeholder_details),
                textColor = contentColor
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            text = stringResource(R.string.about_detail),
            color = contentColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .padding(bottom = MEDIUM_PADDING),
            text = selectedJetpack.about,
            color = contentColor,
            fontSize = MaterialTheme.typography.body1.fontSize,
            maxLines = ABOUT_MAX_LINES_DETAILSCREEN
        )
        Spacer(modifier = Modifier.height(15.dp))
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start
        ) {
            OrderedList(
                title = stringResource(R.string.tags_placeholder_detail),
                items = selectedJetpack.tags,
                textColor = contentColor
            )
        }

    }
}


@Composable
fun BackgroundContent(
    bookImg: String,
    imageFraction: Float = 1f,
    backgroundColor: Color = MaterialTheme.colors.welcomeScreenBackgroundColor,
    onCloseClicked: () -> Unit
) {

    val imageUrl = "$BASE_URL${bookImg}"


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = imageFraction + MINIMUNM_BACKGROUND_IMAGE_HEIGHT)
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
                onClick = { onCloseClicked() }) {
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
val BottomSheetScaffoldState.currentSheetFraction: Float
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


