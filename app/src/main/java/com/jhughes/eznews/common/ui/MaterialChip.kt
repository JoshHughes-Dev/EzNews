package com.jhughes.eznews.common.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jhughes.eznews.common.ProvideAppTheme
import com.jhughes.eznews.common.data.AppTheme
import com.jhughes.eznews.common.theme.EzNewsBackdropContentTheme
import com.jhughes.eznews.common.ui.preview.LightDarkThemePreviewProvider

//todo delete soon

@Preview
@Composable
internal fun ChipPreview(
    @PreviewParameter(LightDarkThemePreviewProvider::class) appTheme: AppTheme
) {
    ProvideAppTheme(appTheme) {
        EzNewsBackdropContentTheme {
            Surface {
                var checked by remember { mutableStateOf(false) }
                MaterialChip(
                    checked = checked,
                    onCheckedChanged = { checked = it },
                    uncheckedAppearance = uncheckedMaterialPillAppearance(
                        borderColor = Color.Green,
                        borderWidth = 4.dp
                    ),
                    checkedAppearance = checkedMaterialPillAppearance(
                        contentColor = Color.Yellow,
                        borderColor = Color.Red,
                        borderWidth = 1.dp
                    )
                ) {
                    Text("Ciao Ivan")
                }
            }
        }
    }
}

@Composable
fun MaterialChip(
    enabled: Boolean = true,
    checked: Boolean,
    checkedAppearance: MaterialPillAppearance = checkedMaterialPillAppearance(),
    uncheckedAppearance: MaterialPillAppearance = uncheckedMaterialPillAppearance(),
    elevation: Dp = 0.dp,
    contentPadding: PaddingValues = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
    onCheckedChanged: (checked: Boolean) -> Unit,
    content: @Composable () -> Unit
) {
    MaterialPill(
        modifier = Modifier
            .clip(CircleShape)
            .toggleable(
                value = checked,
                enabled = enabled,
                role = Role.Checkbox,
                onValueChange = { onCheckedChanged(!checked) }
            ),
        appearance = if (checked) checkedAppearance else uncheckedAppearance,
        elevation = elevation,
        contentPadding = contentPadding,
        content = content
    )
}

@Composable
fun checkedMaterialPillAppearance(
    backgroundColor: Color = MaterialTheme.colors.onSurface,
    contentColor: Color = contentColorFor(backgroundColor),
    borderColor: Color = Color.Transparent,
    borderWidth: Dp = 0.dp
) = MaterialPillAppearance(backgroundColor, contentColor, borderColor, borderWidth)

@Composable
fun uncheckedMaterialPillAppearance(
    backgroundColor: Color = MaterialTheme.colors.onSurface.copy(alpha = .54f),
    contentColor: Color = contentColorFor(backgroundColor),
    borderColor: Color = Color.Transparent,
    borderWidth: Dp = 0.dp
) = MaterialPillAppearance(backgroundColor, contentColor, borderColor, borderWidth)
    data class MaterialPillAppearance(
    val backgroundColor: Color,
    val contentColor: Color,
    val borderColor: Color,
    val borderWidth: Dp
)

@Preview
@Composable
fun PillPreview(
    @PreviewParameter(LightDarkThemePreviewProvider::class) appTheme: AppTheme
) {
    ProvideAppTheme(appTheme) {
        EzNewsBackdropContentTheme {
            Surface {
                MaterialPill {
                    Text("I am a pill hello")
                }
            }
        }
    }
}

@Composable
fun MaterialPill(
    modifier: Modifier = Modifier,
    appearance: MaterialPillAppearance = uncheckedMaterialPillAppearance(),
    elevation: Dp = 0.dp,
    contentPadding: PaddingValues = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
    content: @Composable () -> Unit
) {
    val backgroundColor by animateColorAsState(appearance.backgroundColor)
    val contentColor by animateColorAsState(appearance.contentColor)
    val borderColor by animateColorAsState(appearance.borderColor)
    val borderWidth by animateDpAsState(appearance.borderWidth)

    Surface(
        shape = CircleShape,
        color = backgroundColor,
        contentColor = contentColor,
        border = BorderStroke(borderWidth, borderColor),
        elevation = elevation,
        modifier = modifier
    ) {
        Box(modifier = Modifier.padding(contentPadding)) {
            content()
        }
    }
}