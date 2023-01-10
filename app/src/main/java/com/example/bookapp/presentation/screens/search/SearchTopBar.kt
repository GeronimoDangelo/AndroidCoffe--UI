package com.example.bookapp.presentation.screens.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookapp.R
import com.example.bookapp.ui.theme.TOP_APP_BAR_HEIGHT
import com.example.bookapp.ui.theme.topBarBg
import com.example.bookapp.ui.theme.topBarTxt

@Composable
fun SearchTopBar(
    text: String,
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onCloseClicked: () -> Unit
) {
    SearchWidget(
        text = text,
        onTextChange = onTextChange,
        onSearchClicked = onSearchClicked,
        onCloseClicked = onCloseClicked
    )
}

@Composable
fun SearchWidget(
    text: String,
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onCloseClicked: () -> Unit
) {


    val focusManager = LocalFocusManager.current

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(TOP_APP_BAR_HEIGHT)
            .semantics {
                contentDescription = "SearchWidget"
            },
        elevation = 5.dp,
        color = MaterialTheme.colors.topBarBg
    ) {


        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .semantics { contentDescription = "TextField" },
            value = text,
            onValueChange = { onTextChange(it) },
            placeholder = {
                Text(
                    text = stringResource(R.string.search_placeholder),
                    color = MaterialTheme.colors.topBarTxt.copy(alpha = 0.5f)

                )
            },
            textStyle = TextStyle(
                color = MaterialTheme.colors.topBarTxt
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(

                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.search_icon_in_searchscreen),
                        tint = MaterialTheme.colors.topBarTxt.copy(alpha = 0.4f)
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    modifier = Modifier.semantics {
                        contentDescription = "CloseIcon"
                    },
                    onClick = {
                        if (text.isNotEmpty()) {
                            onTextChange("")
                        } else {
                            onCloseClicked()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.close_icon_in_search_screen),
                        tint = MaterialTheme.colors.topBarTxt
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                    focusManager.clearFocus()
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = MaterialTheme.colors.topBarTxt,
                focusedIndicatorColor = MaterialTheme.colors.topBarTxt.copy(1f),
                unfocusedIndicatorColor = MaterialTheme.colors.topBarTxt.copy(0.6f)
            )

        )
    }
}

@Preview
@Composable
fun prev() {
    SearchWidget(
        text = "Search your topic...",
        onTextChange = {},
        onSearchClicked = {},
        onCloseClicked = {}
    )
}












