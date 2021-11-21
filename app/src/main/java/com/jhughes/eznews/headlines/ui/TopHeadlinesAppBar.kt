package com.jhughes.eznews.headlines.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.height
import androidx.compose.material.BackdropScaffoldDefaults
import androidx.compose.material.BackdropScaffoldState
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.jhughes.eznews.R
import com.jhughes.eznews.common.theme.EzNewsThemeAlt
import com.jhughes.eznews.common.ui.preview.LightDarkThemePreviewProvider

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TopHeadlinesAppBar(
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    scaffoldState: BackdropScaffoldState = BackdropScaffoldState(BackdropValue.Concealed),
    onRequestFilters: () -> Unit = {},
    onRequestSettings: () -> Unit = {},
    onConfirmFilters: () -> Unit = {},
    backgroundColor: Color = MaterialTheme.colors.primarySurface
) {
    Log.d("ComposeTest", "TopHeadlinesAppBar")
    TopAppBar(
        modifier = modifier,
        title = {
            Image(
                modifier = Modifier.height(BackdropScaffoldDefaults.HeaderHeight),
                painter = painterResource(
                    if (!isDarkTheme) {
                        R.drawable.ic_eznews_logo_light
                    } else {
                        R.drawable.ic_eznews_logo_dark
                    }
                ),
                contentDescription = ""
            )
        },
        actions = {
            val canReveal = scaffoldState.isRevealed || scaffoldState.direction > 0f
            val canConceal = scaffoldState.isConcealed || scaffoldState.direction < 0f

            if (canConceal) {
                IconButton(onClick = onRequestFilters) {
                    Icon(imageVector = Icons.Outlined.Tune, contentDescription = "")
                }
                IconButton(onClick = onRequestSettings) {
                    Icon(imageVector = Icons.Outlined.Settings, contentDescription = "")
                }
            } else if(canReveal) {
                IconButton(onClick = onConfirmFilters) {
                    Icon(imageVector = Icons.Outlined.Check, contentDescription = "")
                }
            }
        },
        elevation = 0.dp,
        backgroundColor = backgroundColor
    )
}

@Preview
@Composable
fun TopHeadlinesAppBarPreview(
    @PreviewParameter(LightDarkThemePreviewProvider::class) isDarkTheme : Boolean,
) {
    EzNewsThemeAlt(isDarkTheme) {
        TopHeadlinesAppBar(isDarkTheme = isDarkTheme)
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun TopHeadlinesAppBarPreview2(
    @PreviewParameter(LightDarkThemePreviewProvider::class) isDarkTheme : Boolean,
) {
    EzNewsThemeAlt(isDarkTheme) {
        TopHeadlinesAppBar(isDarkTheme = isDarkTheme, scaffoldState = BackdropScaffoldState(BackdropValue.Revealed))
    }
}