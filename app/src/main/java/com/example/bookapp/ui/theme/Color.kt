package com.example.bookapp.ui.theme

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val LightGray = Color(0xFFFBF9F7)
val DarkGray = Color(0xFF1D1D27)

val titleOnBoardingDay = Color(0xFF70655D)
val titleOnBoardingNight = Color(0xFFF9A826)

val descOnBoardingDay = Color(0xFF7B7875)
val descOnBoardingNight = Color(0xFFD7D7D7)

val indicatorActiveDay = Color(0xFF997B66)
val indicatorActiveNight = Color(0xFFF9A826)

val indicatorInactiveDay = Color(0xFFD4D4D4)
val indicatorInactiveNight = Color(0xFFD9D9D9)

val buttonOnBoardingDay = Color(0xFF745C4C)
val buttonOnBoardingNight = Color(0xFFF9A826)

val buttonOnBoardingDayText = Color(0xFFFBF9F7)
val buttonOnBoardingNightText = Color(0xFF1D1D27)

val homeTopBarTxtDay = Color(0xFF917A67)
val homeTopBarTxtNight = Color(0xFFBFD9FF)

val homeTopBarBGDay = Color(0xFFFBF9F7)
val homeTopBarBGNight = Color(0xFF1D1D27)

val starColor = Color(0xFFF9A826)

//shimmer
val shimmerLightGray = Color(0xFFEBEBEB)
val shimmerMediumGray = Color(0xFFD3D3D3)
val shimmerDarGray = Color(0xFF201F1F)
val shimmerDarGrayRegular = Color(0xFF3A3939)

val errorDay = Color(0xFF949494)
val errorNight = Color(0xFFDBDBDB)





val Colors.welcomeScreenBackgroundColor
    get() = if (isLight) LightGray else DarkGray

val Colors.titleColor
    get() = if (isLight) titleOnBoardingDay else titleOnBoardingNight

val Colors.descriptionColor
    get() = if (isLight) descOnBoardingDay else descOnBoardingNight


val Colors.activeIndicatorColor
    get() = if (isLight) indicatorActiveDay else indicatorActiveNight

val Colors.inactiveIndicatorColor
    get() = if (isLight) indicatorInactiveDay else indicatorInactiveNight

val Colors.buttonBackgroundColor
    get() = if (isLight) buttonOnBoardingDay else buttonOnBoardingNight

val Colors.contentBtnText
get() = if (isLight) buttonOnBoardingDayText else buttonOnBoardingNightText

val Colors.topBarTxt
    get() = if (isLight) homeTopBarTxtDay else homeTopBarTxtNight

val Colors.topBarBg
    get() = if (isLight) homeTopBarBGDay else homeTopBarBGNight

//shimmer
val Colors.ShimmerItem
    get() = if (isLight) shimmerLightGray else shimmerDarGray

//shimmer placeholders
val Colors.ShimmerPlaceholders
    get() = if (isLight) shimmerMediumGray else shimmerDarGrayRegular

//error internet and server placeholders
val Colors.ErrorsPlaceholders
    get() = if (isLight) errorDay else errorNight