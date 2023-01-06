package com.example.bookapp.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
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
import androidx.compose.ui.text.style.TextAlign
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
import com.example.bookapp.domain.model.Book
import com.example.bookapp.navigation.Screen
import com.example.bookapp.presentation.components.RatingWidget
import com.example.bookapp.presentation.components.ShimmerEffect
import com.example.bookapp.ui.theme.*
import com.example.bookapp.util.Constants.BASE_URL

@Composable
fun ListContent(
    books: LazyPagingItems<Book>,
    navHostController: NavHostController
) {
    val result = handlePagingResult(books = books)

    Log.d("ListContent", books.loadState.toString())


    if (result) {
        LazyColumn(
            contentPadding = PaddingValues(all = SMALL_PADDING),
            verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
        ) {
            items(
                items = books,
                key = { book ->
                    book.id
                }
            ) { book ->
                book?.let {
                    BookItem(book = it, navHostController = navHostController)
                }
            }
        }
    }


}


@Composable
fun handlePagingResult(
    books: LazyPagingItems<Book>
): Boolean {
    books.apply {
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
                EmptyScreen(error = error, books = books)
                false
            }
            books.itemCount < 1 -> {
                EmptyScreen()
                false
            }
            else -> true
        }
    }
}


@Composable
fun BookItem(
    book: Book,
    navHostController: NavHostController
) {
    Box(
        modifier = Modifier
            .height(BOOK_ITEM_HEIGHT)
            .clickable {
                navHostController.navigate(Screen.Details.passBookId(bookId = book.id))
            },
        contentAlignment = Alignment.BottomStart
    ) {
        Surface(shape = RoundedCornerShape(size = LARGE_PADDING)) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data = "$BASE_URL${book.image}")
                    .placeholder(drawableResId = R.drawable.placeholder)
                    .error(drawableResId = R.drawable.placeholder)
                    .build(), contentDescription = stringResource(R.string.book_image),
                contentScale = ContentScale.Crop
            )
        }
        //
        //Content Description
        //
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.4f)
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
                    .padding(all = MEDIUM_PADDING)
            ) {
                Text(
                    text = book.name,
                    color = Color.White,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(modifier = Modifier.padding(top = 3.dp),
                    text = book.about,
                    color = Color.White.copy(0.4f),
                    fontSize = MaterialTheme.typography.subtitle2.fontSize,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )/*
                Row(
                    modifier = Modifier.padding(top = SMALL_PADDING),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RatingWidget(
                        modifier = Modifier.padding(end = SMALL_PADDING),
                        rating = book.rating
                    )
                    Text(
                        text = "(${book.rating})",
                        textAlign = TextAlign.Center,
                        color = Color.White.copy(0.5f)
                    )
                }*/
            }
        }
    }
}

@Preview
@Composable
fun prev() {
    BookItem(
        book = Book(
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

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun prev2() {
    BookItem(
        book = Book(
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