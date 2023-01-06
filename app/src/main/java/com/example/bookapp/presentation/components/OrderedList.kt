package com.example.bookapp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookapp.ui.theme.topBarTxt

@Composable
fun OrderedList(
    title: String,
    items: List<String>,
    textColor: Color
) {
    Column {
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = title,
            color = textColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold
        )
        Column {
            items.forEach { item ->


                Text(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    text = item,
                    color = textColor,
                    fontSize = MaterialTheme.typography.body1.fontSize,
                )
                Spacer(modifier = Modifier.height(12.dp))


            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    OrderedList(
        title = "Nihon",
        items = listOf("kyoto", "oosaaka"),
        textColor = MaterialTheme.colors.topBarTxt
    )
}