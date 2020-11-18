package com.jhughes.eznews.data.remote.model

import com.jhughes.eznews.domain.model.Article
import com.squareup.moshi.Json
import java.util.*

data class NewsArticle(
    @field:Json(name = "source") val source: NewsSource,
    @field:Json(name = "author") val author: String? = null,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "description") val description: String? = null,
    @field:Json(name = "url") val url: String? = null,
    @field:Json(name = "urlToImage") val urlToImage: String? = null,
    @field:Json(name = "publishedAt") val publishedAt: Date
)

fun NewsArticle.toDomain(): Article {
    return Article(
        source = source.toDomain(),
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt
    )
}