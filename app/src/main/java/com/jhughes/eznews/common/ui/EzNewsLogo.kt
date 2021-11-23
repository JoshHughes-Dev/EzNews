package com.jhughes.eznews.common.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.jhughes.eznews.R
import com.jhughes.eznews.common.isAppInDarkTheme

@Composable
fun EzNewsLogo(modifier : Modifier = Modifier) {
    Image(
        modifier = modifier.fillMaxHeight(),
        painter = painterResource(
            if (!isAppInDarkTheme()) {
                R.drawable.ic_eznews_logo_light
            } else {
                R.drawable.ic_eznews_logo_dark
            }
        ),
        contentDescription = ""
    )
}