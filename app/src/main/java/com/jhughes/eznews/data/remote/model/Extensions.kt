package com.jhughes.eznews.data.remote.model

import com.jhughes.eznews.domain.model.NewsCategory
import java.util.*

fun NewsCategory.toQueryParamValue(): String? {
    return if (this != NewsCategory.ALL) {
        name.toLowerCase(Locale.ENGLISH)
    } else {
        null
    }
}