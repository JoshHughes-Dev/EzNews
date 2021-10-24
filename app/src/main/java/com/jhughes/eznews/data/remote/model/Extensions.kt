package com.jhughes.eznews.data.remote.model

import com.jhughes.eznews.domain.model.NewsCategory

fun NewsCategory.toQueryParamValue(): String? {
    return if (this != NewsCategory.ALL) {
        name.lowercase()
    } else {
        null
    }
}