package com.jhughes.eznews.headlines.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.jhughes.eznews.common.theme.EzNewsTheme
import com.jhughes.eznews.domain.model.Country

@Composable
fun SelectCountry(modifier: Modifier = Modifier, onSelectCountry: (Country) -> Unit = {}) {
    Column {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.h6,
            text = "Select a Country"
        )
        Divider()
        LazyColumnFor(modifier = modifier, items = Country.values().toList()) { country ->
            Row(
                modifier = Modifier
                    .preferredHeight(48.dp)
                    .fillMaxWidth()
                    .clickable(onClick = { onSelectCountry(country) })
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = country.name.toString()
                )
            }
        }
    }
}

@Preview
@Composable
fun SelectCountryPreview() {
    EzNewsTheme {
        Surface {
            SelectCountry()
        }
    }
}