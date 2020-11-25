package com.jhughes.eznews.domain.model

import androidx.annotation.StringRes
import com.jhughes.eznews.R

enum class NewsCategory(@StringRes val res : Int) {
    ALL(R.string.all),
    BUSINESS(R.string.business),
    ENTERTAINMENT(R.string.entertainment),
    HEALTH(R.string.health),
    SCIENCE(R.string.science),
    SPORTS(R.string.sport),
    TECHNOLOGY(R.string.technology)
}

fun NewsCategory.emoji() : String {
    return when(this) {
        NewsCategory.ALL -> String(Character.toChars(0x1F4F0))
        NewsCategory.BUSINESS -> String(Character.toChars(0x1F4C8))
        NewsCategory.ENTERTAINMENT -> String(Character.toChars(0x1F3AD))
        NewsCategory.HEALTH -> String(Character.toChars(0x1FA7A))
        NewsCategory.SCIENCE -> String(Character.toChars(0x1F52C))
        NewsCategory.SPORTS -> String(Character.toChars(0x1F3BE))
        NewsCategory.TECHNOLOGY -> String(Character.toChars(0x1F4BB))
    }
}