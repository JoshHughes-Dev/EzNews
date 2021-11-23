package com.jhughes.eznews.news.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp


@Composable
fun KeywordFilter(
    modifier: Modifier = Modifier,
    label : String = "Keywords or a phrase to search for",
    value : String = "",
    onValueChange : (String) -> Unit = { },
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
) {
    FilterSection(
        modifier = modifier,
        title = "Keyword"
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            trailingIcon = {
                if (value.isNotEmpty()) {
                    IconButton(onClick = { onValueChange("") }) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "")
                    }
                }
            },
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = MaterialTheme.colors.onPrimary.copy(alpha = ContentAlpha.high),
                focusedIndicatorColor = MaterialTheme.colors.onPrimary.copy(alpha = ContentAlpha.high),
                focusedLabelColor = MaterialTheme.colors.onPrimary.copy(alpha = ContentAlpha.high)
            )
        )
    }
}