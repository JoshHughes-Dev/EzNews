package com.jhughes.eznews.domain.model

import java.util.*

data class Article(
    val source: Source,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: Date
) {
    companion object {
        fun empty() = Article(Source("", ""), "", "", "", "", "", Date())
    }
}