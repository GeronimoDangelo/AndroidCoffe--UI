package com.example.bookapp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookapp.R
import com.example.bookapp.ui.theme.ICONS_INFOBOX_SIZE
import com.example.bookapp.ui.theme.SMALL_PADDING
import com.example.bookapp.ui.theme.topBarTxt

@Composable
fun InfoBox(
    icon: Painter,
    iconColor: Color,
    bigText: String,
    smallText: String,
    textColor: Color
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(modifier = Modifier.padding(end = 8.dp).size(ICONS_INFOBOX_SIZE),
            painter = icon,
            contentDescription = stringResource(R.string.info_icon_details),
            tint = iconColor
        )
        Column {
            Text(
                text = bigText,
                color = textColor,
                fontSize = MaterialTheme.typography.h6.fontSize,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.alpha(ContentAlpha.medium),
                text = smallText,
                color = textColor,
                fontSize = MaterialTheme.typography.subtitle2.fontSize,
            )
        }
    }

}

@Composable
@Preview(showBackground = true)
fun prev() {
    InfoBox(
        icon = painterResource(id = R.drawable.no),
        iconColor = MaterialTheme.colors.topBarTxt,
        bigText = "23",
        smallText ="Travel",
        textColor = MaterialTheme.colors.topBarTxt
    )
}