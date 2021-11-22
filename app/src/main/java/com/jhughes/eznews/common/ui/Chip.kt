package com.jhughes.eznews.common.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jhughes.eznews.common.ProvideAppTheme
import com.jhughes.eznews.common.data.AppTheme
import com.jhughes.eznews.common.isAppInDarkTheme
import com.jhughes.eznews.common.theme.EzNewsBackdropContentTheme
import com.jhughes.eznews.common.ui.preview.LightDarkThemePreviewProvider

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    label: String,
    contentDescription: String,
    appearance: ChipAppearance = uncheckedChipAppearance(),
    elevation: Dp = 8.dp,
    startIcon: () -> ImageVector? = { null },
    isStartIconEnabled: Boolean = false,
    onStartIconClicked: () -> Unit = { },
    endIcon: () -> ImageVector? = { null },
    isEndIconEnabled: Boolean = false,
    onEndIconClicked: () -> Unit = { },
    isClickable: Boolean = false,
    onClick: () -> Unit = { }
) {
    val backgroundColor by animateColorAsState(appearance.backgroundColor)
    val contentColor by animateColorAsState(appearance.contentColor)

    Surface(
        modifier = modifier
            .clip(CircleShape)
            .clickable(enabled = isClickable, onClick = { onClick() }),
        elevation = elevation,
        shape = CircleShape,
        color = backgroundColor
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            val leader = startIcon()
            val trailer = endIcon()

            if (leader != null) {
                Icon(
                    leader,
                    contentDescription = contentDescription,
                    tint = contentColor,
                    modifier = Modifier
                        .clickable(enabled = isStartIconEnabled, onClick = onStartIconClicked)
                        .padding(start = 4.dp)
                )
            }

            Text(
                label,
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.button.copy(color = contentColor)
            )

            if (trailer != null) {
                Icon(
                    trailer,
                    contentDescription = contentDescription,
                    tint = contentColor,
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
    isDarkTheme: Boolean = isAppInDarkTheme(),
    label: String,
    contentDescription: String,
    selected: Boolean,
    onClick: (nowSelected: Boolean) -> Unit,
    elevation: Dp = 4.dp,
) {
    val appearance = if (selected) {
        if (isDarkTheme) {
            checkedChipAppearance(
                backgroundColor = MaterialTheme.colors.onSurface,
                contentColor = MaterialTheme.colors.surface
            )
        } else {
            checkedChipAppearance(
                backgroundColor = MaterialTheme.colors.primaryVariant,
                contentColor = MaterialTheme.colors.onSurface
            )
        }
    } else {
        uncheckedChipAppearance(
            backgroundColor = MaterialTheme.colors.surface
        )
    }

    Chip(
        modifier = modifier,
        label = label,
        contentDescription = contentDescription,
        appearance = appearance,
        elevation = elevation,
        isClickable = true,
        onClick = { onClick(!selected) },
    )
}

@Composable
fun checkedChipAppearance(
    backgroundColor: Color = MaterialTheme.colors.onSurface,
    contentColor: Color = contentColorFor(backgroundColor),
) = ChipAppearance(backgroundColor, contentColor)

@Composable
fun uncheckedChipAppearance(
    backgroundColor: Color = MaterialTheme.colors.onSurface.copy(alpha = .54f),
    contentColor: Color = contentColorFor(backgroundColor),
) = ChipAppearance(backgroundColor, contentColor)

data class ChipAppearance(
    val backgroundColor: Color,
    val contentColor: Color
)

@Preview
@Composable
fun SelectableChipPreview(
    @PreviewParameter(LightDarkThemePreviewProvider::class) appTheme: AppTheme
) {
    ProvideAppTheme(appTheme) {
        EzNewsBackdropContentTheme {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colors.primary)
                    .padding(8.dp)
            ) {
                SelectableChip(
                    label = "Chip",
                    isDarkTheme = appTheme == AppTheme.DARK,
                    contentDescription = "",
                    selected = false,
                    onClick = {})
            }
        }
    }
}

@Preview
@Composable
fun SelectableChipPreview2(
    @PreviewParameter(LightDarkThemePreviewProvider::class) appTheme: AppTheme
) {
    ProvideAppTheme(appTheme) {
        EzNewsBackdropContentTheme {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colors.primary)
                    .padding(8.dp)
            ) {
                SelectableChip(
                    label = "Chip",
                    isDarkTheme = appTheme == AppTheme.DARK,
                    contentDescription = "",
                    selected = true,
                    onClick = {})
            }
        }
    }
}
