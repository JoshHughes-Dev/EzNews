package com.jhughes.eznews.common.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jhughes.eznews.common.theme.EzNewsThemeAlt
import com.jhughes.eznews.common.ui.preview.LightDarkThemePreviewProvider

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    label: String,
    contentDescription: String,
    color: Color = MaterialTheme.colors.surface,
    elevation: Dp = 8.dp,
    labelColor: Color = contentColorFor(color),
    startIcon: () -> ImageVector? = { null },
    isStartIconEnabled: Boolean = false,
    startIconTint: Color = contentColorFor(color),
    onStartIconClicked: () -> Unit = { },
    endIcon: () -> ImageVector? = { null },
    isEndIconEnabled: Boolean = false,
    endIconTint: Color = contentColorFor(color),
    onEndIconClicked: () -> Unit = { },
    isClickable: Boolean = false,
    onClick: () -> Unit = { }
) {
    val shape = MaterialTheme.shapes.small

    Surface(
        modifier = modifier
            .clip(shape)
            .clickable(enabled = isClickable, onClick = { onClick() }),
        elevation = elevation,
        shape = shape,
        color = color
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            val leader = startIcon()
            val trailer = endIcon()

            if (leader != null) {
                Icon(
                    leader,
                    contentDescription = contentDescription,
                    tint = startIconTint,
                    modifier = Modifier
                        .clickable(enabled = isStartIconEnabled, onClick = onStartIconClicked)
                        .padding(start = 4.dp)
                )
            }

            Text(
                label,
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.button.copy(color = labelColor)
            )

            if (trailer != null) {
                Icon(
                    trailer,
                    contentDescription = contentDescription,
                    tint = endIconTint,
                    modifier = Modifier
                        .clickable(enabled = isEndIconEnabled, onClick = onEndIconClicked)
                        .padding(end = 4.dp)
                )
            }

        }
    }
}

@Composable
fun SelectableChip(
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    label: String,
    contentDescription: String,
    selected: Boolean,
    onClick: (nowSelected: Boolean) -> Unit,
    elevation: Dp = 4.dp,
) {
    Chip(
        modifier = modifier,
        label = label,
        contentDescription = contentDescription,
        color = if (isDarkTheme) {
            if (selected) {
                MaterialTheme.colors.onSurface
            } else {
                MaterialTheme.colors.surface
            }
        } else {
            if (selected) {
                MaterialTheme.colors.primaryVariant
            } else {
                MaterialTheme.colors.surface
            }
        },
        labelColor = if (isDarkTheme) {
            if (selected) {
                MaterialTheme.colors.surface
            } else {
                MaterialTheme.colors.onSurface
            }
        } else {
            MaterialTheme.colors.onSurface
        },
        elevation = elevation,
        startIcon = { if (selected) Icons.Default.Check else null },
        startIconTint = if (isDarkTheme) {
            if (selected) {
                MaterialTheme.colors.surface
            } else {
                MaterialTheme.colors.onSurface
            }
        } else {
            MaterialTheme.colors.onSurface
        },
        isClickable = true,
        onClick = { onClick(!selected) },
    )
}

@Preview
@Composable
fun SelectableChipPreview(
    @PreviewParameter(LightDarkThemePreviewProvider::class) isDarkTheme: Boolean
) {
    EzNewsThemeAlt(isDarkTheme) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colors.primary)
                .padding(8.dp)
        ) {
            SelectableChip(
                label = "Chip",
                isDarkTheme = isDarkTheme,
                contentDescription = "",
                selected = false,
                onClick = {})
        }
    }
}

@Preview
@Composable
fun SelectableChipPreview2(
    @PreviewParameter(LightDarkThemePreviewProvider::class) isDarkTheme: Boolean
) {
    EzNewsThemeAlt(isDarkTheme) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colors.primary)
                .padding(8.dp)
        ) {
            SelectableChip(
                label = "Chip",
                isDarkTheme = isDarkTheme,
                contentDescription = "",
                selected = true,
                onClick = {})
        }
    }
}
