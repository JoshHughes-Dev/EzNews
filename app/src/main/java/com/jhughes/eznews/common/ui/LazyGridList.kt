package com.jhughes.eznews.common.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun <T> LazyGridFor(
    items: List<T>,
    rowSize: Int = 1,
    itemContent: @Composable BoxScope.(T) -> Unit,
) {
    val rows = items.chunked(rowSize)
    LazyColumn {
        items(rows) { row ->
            Row(Modifier.fillParentMaxWidth()) {
                for ((index, item) in row.withIndex()) {
                    Box(Modifier.fillMaxWidth(1f / (rowSize - index))) {
                        itemContent(item)
                    }
                }
            }
        }
    }
}