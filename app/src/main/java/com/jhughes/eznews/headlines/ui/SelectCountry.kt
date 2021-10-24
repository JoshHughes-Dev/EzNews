package com.jhughes.eznews.headlines.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jhughes.eznews.common.theme.EzNewsTheme
import com.jhughes.eznews.common.ui.LazyGridFor
import com.jhughes.eznews.common.utils.toFlagEmoji
import com.jhughes.eznews.domain.model.Country
import com.jhughes.eznews.R
import com.jhughes.eznews.domain.model.NewsCategory
import com.jhughes.eznews.domain.model.emoji

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SelectCountry(modifier: Modifier = Modifier, onSelectCountry: (Country) -> Unit = {}) {
    Column(modifier = modifier.padding(bottom = 8.dp)) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp),
            style = MaterialTheme.typography.h6,
            text = stringResource(id = R.string.select_country)
        )
        Divider()

        LazyVerticalGrid(
            cells = GridCells.Fixed(3),
        ) {
            items(Country.usableSubSet().toList()) { country ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(74.dp)
                        .clickable(onClick = { onSelectCountry(country) })
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = country.countryCode.toFlagEmoji())
                    Text(text = stringResource(id = country.res))
                }
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