package com.jhughes.eznews.news.ui

import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BackdropScaffoldDefaults
import androidx.compose.material.BackdropScaffoldState
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.primarySurface
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.jhughes.eznews.common.ProvideAppTheme
import com.jhughes.eznews.common.data.AppTheme
import com.jhughes.eznews.common.theme.EzNewsBackdropContentTheme
import com.jhughes.eznews.common.theme.EzNewsBackdropTheme
import com.jhughes.eznews.common.ui.preview.LightDarkThemePreviewProvider


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsBackdropScaffold(
    modifier: Modifier = Modifier,
    scaffoldState: BackdropScaffoldState = rememberBackdropScaffoldState(BackdropValue.Concealed),
    appBar: @Composable () -> Unit = {},
    backLayerContent: @Composable () -> Unit = {},
    frontLayerContent: @Composable () -> Unit = {},
) {
    EzNewsBackdropTheme {
        BackdropScaffold(
            modifier = modifier,
            scaffoldState = scaffoldState,
            gesturesEnabled = false,
            persistentAppBar = true,
            peekHeight = BackdropScaffoldDefaults.PeekHeight + 8.dp,
            appBar = {
                EzNewsBackdropContentTheme {
                    appBar()
                }
            },
            backLayerContent = {
                EzNewsBackdropContentTheme {
                    backLayerContent()
                }
            },
            backLayerBackgroundColor = MaterialTheme.colors.primarySurface,
            frontLayerContent = frontLayerContent
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun NewsBackdropScaffoldPreview(
    @PreviewParameter(LightDarkThemePreviewProvider::class) appTheme: AppTheme
) {
    ProvideAppTheme(appTheme) {
        NewsBackdropScaffold()
    }
}