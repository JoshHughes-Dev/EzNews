package com.jhughes.eznews.common.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class LightDarkThemePreviewProvider : PreviewParameterProvider<Boolean> {
    override val values = sequenceOf(false, true)
}