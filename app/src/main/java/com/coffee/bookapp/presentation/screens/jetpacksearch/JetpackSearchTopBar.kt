package com.coffee.bookapp.presentation.screens.jetpacksearch

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.coffee.bookapp.R
import com.coffee.bookapp.presentation.screens.search.SearchWidget
import com.coffee.bookapp.ui.theme.TOP_APP_BAR_HEIGHT
import com.coffee.bookapp.ui.theme.topBarBg
import com.coffee.bookapp.ui.theme.topBarTxt

@Composable
fun JetpackSearchTopBar(
    texts: String,
    onTextChangeJetpack: (String) -> Unit,
    onSearchClickedJetpack: (String) -> Unit,
    onCloseClickedJetpack: () -> Unit
) {
    SearchJetpackWidget(
        texts = texts,
        onTextChangeJetpack = onTextChangeJetpack,
        onSearchClickedJetpack = onSearchClickedJetpack,
        onCloseClickedJetpack = onCloseClickedJetpack
    )
}

@Composable
fun SearchJetpackWidget(
    texts: String,
    onTextChangeJetpack: (String) -> Unit,
    onSearchClickedJetpack: (String) -> Unit,
    onCloseClickedJetpack: () -> Unit
) {

    val focusManagers = LocalFocusManager.current

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(TOP_APP_BAR_HEIGHT),
        elevation = 5.dp,
        color = MaterialTheme.colors.topBarBg
    ) {


        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = texts,
            onValueChange = { onTextChangeJetpack(it) },
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
                    onClick = {
                        if (texts.isNotEmpty()) {
                            onTextChangeJetpack("")
                        } else {
                            onCloseClickedJetpack()
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
                    onSearchClickedJetpack(texts)
                    focusManagers.clearFocus()
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
fun Preview() {
    SearchWidget(
        text = "Search your topic...",
        onTextChange = {},
        onSearchClicked = {},
        onCloseClicked = {}
    )
}

