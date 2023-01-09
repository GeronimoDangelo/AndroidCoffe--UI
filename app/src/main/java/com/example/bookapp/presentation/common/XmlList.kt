package com.example.bookapp.presentation.common

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookapp.R
import com.example.bookapp.domain.model.XmlModel
import com.example.bookapp.navigation.Screen
import com.example.bookapp.presentation.components.ShimmerEffect
import com.example.bookapp.ui.theme.BOOK_ITEM_HEIGHT
import com.example.bookapp.ui.theme.LARGE_PADDING
import com.example.bookapp.ui.theme.MEDIUM_PADDING
import com.example.bookapp.ui.theme.SMALL_PADDING
import com.example.bookapp.util.Constants
import com.example.bookapp.util.Constants.BASE_URL

@Composable
fun XmlList(
    xmls: LazyPagingItems<XmlModel>,
    navHostController: NavHostController
) {

    val result = handlePagingXmlResult(xmls = xmls)
    Log.d("Xml", xmls.loadState.toString())


    if (result) {
        LazyColumn(
            contentPadding = PaddingValues(all = SMALL_PADDING),
            verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
        ) {
            items(
                items = xmls,
                key = { xml ->
                    xml.id
                }
            ) { xmlItem ->
                xmlItem?.let {
                    XmlItem(xmls = it, navHostController = navHostController)
                }
            }
        }
    }

}

@Composable
fun handlePagingXmlResult(xmls: LazyPagingItems<XmlModel>): Boolean {
    xmls.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when {
            loadState.refresh is LoadState.Loading -> {
                ShimmerEffect()
                false
            }
            error != null -> {
                EmptyScreen(error = error, xmls = xmls)
                false
            }
            xmls.itemCount < 1 -> {
                EmptyScreen()
                false
            }
            else -> true
        }
    }
}


@Composable
fun XmlItem(
    xmls: XmlModel,
    navHostController: NavHostController
) {
    Box(
        modifier = Modifier
            .height(BOOK_ITEM_HEIGHT)
            .clickable {
                navHostController.navigate(Screen.DetailsXml.passXmlId(xmlId = xmls.id))
            },
        contentAlignment = Alignment.BottomStart
    ) {
        Surface(shape = RoundedCornerShape(size = LARGE_PADDING)) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data = "${BASE_URL}${xmls.image}")
                    .placeholder(drawableResId = R.drawable.placeholder)
                    .error(drawableResId = R.drawable.placeholder)
                    .build(), contentDescription ="",
                contentScale = ContentScale.Crop
            )
        }
        //
        //Content Description
        //
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.35f)
                .fillMaxWidth(),
            color = Color.Black.copy(ContentAlpha.medium),
            shape = RoundedCornerShape(
                bottomStart = LARGE_PADDING,
                bottomEnd = LARGE_PADDING
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, end = 16.dp, top = 10.dp)
            ) {
                Text(
                    text = xmls.name,
                    color = Color.White,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    modifier = Modifier.padding(top = 3.dp),
                    text = xmls.about,
                    color = Color.White.copy(0.4f),
                    fontSize = MaterialTheme.typography.subtitle2.fontSize,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview
@Composable
fun PrevXmlList() {
    XmlItem(
        xmls = XmlModel(
            id = 1,
            name = "flows",
            image = "",
            about = "dario and diego traveling to japan in this february 2023 :D yeyyy we are gonna do it yeeeeyy",
            rating = 5.0,
            level = "february",
            timeToLearn = "monday",
            tags = listOf()
        ), navHostController = rememberNavController()
    )
}