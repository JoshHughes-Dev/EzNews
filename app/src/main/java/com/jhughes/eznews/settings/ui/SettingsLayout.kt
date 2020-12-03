package com.jhughes.eznews.settings.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.jhughes.eznews.common.theme.EzNewsTheme
import com.jhughes.eznews.common.utils.SysUiController
import com.jhughes.eznews.R
import dev.chrisbanes.accompanist.insets.navigationBarsPadding
import dev.chrisbanes.accompanist.insets.statusBarsPadding


@Composable
fun SettingsLayout(closeSettings: () -> Unit) {
    with(SysUiController.current) {
        setStatusBarColor(MaterialTheme.colors.primarySurface)
        setNavigationBarColor(Color.Transparent)
    }

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.settings)) },
            modifier = Modifier.statusBarsPadding(),
            navigationIcon = {
                IconButton(onClick = closeSettings) {
                    Icon(imageVector = Icons.Default.ArrowBack)
                }
            }
        )
    }) {
        Column(Modifier.navigationBarsPadding().padding(20.dp)) {
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = stringResource(id = R.string.about_title),
                style = MaterialTheme.typography.h6
            )
            Text(text = stringResource(id = R.string.about_content))
        }
    }
}

@Preview
@Composable
fun SettingsLayoutPreview() {
    EzNewsTheme {
        SettingsLayout(closeSettings = {})
    }
}