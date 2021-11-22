package com.jhughes.eznews.common.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.jhughes.eznews.common.data.AppTheme

class LightDarkThemePreviewProvider : PreviewParameterProvider<AppTheme> {
    override val values = sequenceOf(AppTheme.LIGHT, AppTheme.DARK)
}