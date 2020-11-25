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