package com.jhughes.eznews.news.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jhughes.eznews.news.ui.headlines.HeadlineTitleText

@Composable
fun FilterSection(
    modifier : Modifier = Modifier,
    title : String = "Filter",
    content : @Composable () -> Unit = {}
) {
    Column(modifier.padding(vertical = 12.dp)) {
        HeadlineTitleText(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .padding(bottom = 12.dp),
            color = MaterialTheme.colors.onBackground,
            text = title
        )
        content()
        Divider(modifier = Modifier.padding(start = 8.dp, top = 8.dp))
    }
}