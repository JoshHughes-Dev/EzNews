package com.jhughes.eznews.data.remote.model

import com.jhughes.eznews.domain.model.Source
import com.squareup.moshi.Json

data class NewsSource(
    @field:Json(name ="id") val id: String? = null,
    @field:Json(name ="name") val name: String? = null
)

fun NewsSource.toDomain() : Source {
    return Source(id = id, name = name)
}
