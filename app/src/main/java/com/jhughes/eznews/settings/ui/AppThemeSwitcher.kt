package com.jhughes.eznews.settings.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.jhughes.eznews.common.ProvideAppTheme
import com.jhughes.eznews.common.data.AppTheme
import com.jhughes.eznews.common.theme.EzNewsTheme
import com.jhughes.eznews.common.ui.preview.LightDarkThemePreviewProvider

@Composable
fun AppThemeSwitcher(
    modifier: Modifier = Modifier,
    currentAppTheme: AppTheme,
    onAppThemeChange: (AppTheme) -> Unit = {}
) {
    Column(
        modifier = modifier.selectableGroup()
    ) {
        AppTheme.values().forEach { appTheme ->
            RadioGroupItem(
                modifier = Modifier.fillMaxWidth(),
                label = when (appTheme) {
                    AppTheme.LIGHT -> "Light"
                    AppTheme.DARK -> "Dark"
                    AppTheme.SYSTEM -> "System"
                },
                value = appTheme,
                selected = currentAppTheme == appTheme,
                onClick = { onAppThemeChange(appTheme) }
            )
        }
    }
}

@Composable
private fun <T> RadioGroupItem(
    modifier: Modifier = Modifier,
    label: String,
    value: T,
    selected: Boolean,
    onClick: (T) -> Unit = {}
) {
    Row(
        modifier = modifier
            .selectable(
                selected = selected,
                onClick = { onClick(value) },
                role = Role.RadioButton
            )
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = null,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.body1,
        )
    }
}

@Preview
@Composable
fun AppThemeSwitcherPreview(
    @PreviewParameter(LightDarkThemePreviewProvider::class) appTheme: AppTheme
) {
    ProvideAppTheme(appTheme) {
        EzNewsTheme {
            Surface {
                AppThemeSwitcher(currentAppTheme = appTheme)
            }
        }
    }
}