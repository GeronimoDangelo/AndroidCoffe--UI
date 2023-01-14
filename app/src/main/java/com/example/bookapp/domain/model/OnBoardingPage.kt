package com.example.bookapp.domain.model

import androidx.annotation.DrawableRes
import com.example.bookapp.R


sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    object First : OnBoardingPage(
        image = R.drawable.first,
        title = "Road to Knowledge",
        description = "Learn with the last and best examples from the official documents!"
    )

    object Second : OnBoardingPage(
        image = R.drawable.second,
        title = "Take Notes",
        description = "Find what you want to learn, take some notes and share your notes with everyone!"
    )

    object Third : OnBoardingPage(
        image = R.drawable.third,
        title = "Take it slowly",
        description = "If the information does not upload at first time , try to return to the categories section and then navigate again into the category you want!"
    )
}
