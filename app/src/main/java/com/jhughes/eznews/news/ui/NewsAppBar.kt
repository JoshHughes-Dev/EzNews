package com.jhughes.eznews.news.ui

import android.util.Log
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.jhughes.eznews.common.ProvideAppTheme
import com.jhughes.eznews.common.data.AppTheme
import com.jhughes.eznews.common.theme.EzNewsBackdropContentTheme
import com.jhughes.eznews.common.ui.EzNewsLogo
import com.jhughes.eznews.common.ui.preview.LightDarkThemePreviewProvider

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TopHeadlinesAppBar(
    modifier: Modifier = Modifier,
    scaffoldState: BackdropScaffoldState = BackdropScaffoldState(BackdropValue.Concealed),
    onRequestFilters: () -> Unit = {},
    onRequestSettings: () -> Unit = {},
    onConfirmFilters: () -> Unit = {}
) {
    Log.d("ComposeTest", "TopHeadlinesAppBar")
    TopAppBar(
        modifier = modifier,
        title = {
            EzNewsLogo(modifier.fillMaxHeight())
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
            } else if (canReveal) {
                IconButton(onClick = onConfirmFilters) {
                    Icon(imageVector = Icons.Outlined.Check, contentDescription = "")
                }
            }
        },
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.background
    )
}

@ExperimentalMaterialApi
@Preview
@Composable
fun TopHeadlinesAppBarPreview(
    @PreviewParameter(LightDarkThemePreviewProvider::class) appTheme: AppTheme
) {
    ProvideAppTheme(currentAppTheme = appTheme) {
        EzNewsBackdropContentTheme(appTheme) {
            TopHeadlinesAppBar()
        }
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun TopHeadlinesAppBarPreview2(
    @PreviewParameter(LightDarkThemePreviewProvider::class) appTheme: AppTheme
) {
    ProvideAppTheme(currentAppTheme = appTheme) {
        EzNewsBackdropContentTheme(appTheme) {
            TopHeadlinesAppBar(
                scaffoldState = BackdropScaffoldState(BackdropValue.Revealed)
            )
        }
    }
}