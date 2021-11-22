package com.jhughes.eznews.settings.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.chuckerteam.chucker.api.Chucker
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jhughes.eznews.BuildConfig
import com.jhughes.eznews.R
import com.jhughes.eznews.common.data.AppTheme
import com.jhughes.eznews.common.ui.TopScrimmedAppBar
import com.jhughes.eznews.settings.SettingsViewModel
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(viewModel: SettingsViewModel, closeSettings: () -> Unit) {

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(color = Color.Transparent)
    }

    val prefTheme = viewModel.appTheme.collectAsState(initial = AppTheme.SYSTEM)
    val scope = rememberCoroutineScope()

    Scaffold(topBar = {
        TopScrimmedAppBar(
            title = { Text(text = stringResource(id = R.string.settings)) },
            navigationIcon = {
                IconButton(onClick = closeSettings) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                }
            }
        )
    }) {
        Column(
            Modifier
                .navigationBarsPadding()
                .padding(16.dp)
        ) {
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = stringResource(id = R.string.about_title),
                style = MaterialTheme.typography.h6
            )
            Text(
                text = stringResource(id = R.string.about_content)
            )
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = "App Theme",
                style = MaterialTheme.typography.h6
            )
            AppThemeSwitcher(
                currentAppTheme = prefTheme.value,
                onAppThemeChange = { scope.launch { viewModel.saveAppThemeTheme(it) } }
            )
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            if (BuildConfig.DEBUG) {
                Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = "Http logger",
                    style = MaterialTheme.typography.h6
                )
                val context = LocalContext.current
                Button(onClick = {
                    context.startActivity(Chucker.getLaunchIntent(context))
                }) {
                    Text(text = "Open Logger")
                }
            }
        }
    }
}


//
//@Preview(showSystemUi = true)
//@Composable
//fun SettingsLayoutPreview() {
//    EzNewsTheme {
//        SettingsLayout(closeSettings = {})
//    }
//}