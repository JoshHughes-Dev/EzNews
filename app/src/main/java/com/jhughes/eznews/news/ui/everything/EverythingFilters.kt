package com.jhughes.eznews.news.ui.everything

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EverythingFilters(modifier : Modifier = Modifier) {
    Text(modifier = Modifier
        .height(50.dp)
        .fillMaxWidth(), text = "todo everything")
}