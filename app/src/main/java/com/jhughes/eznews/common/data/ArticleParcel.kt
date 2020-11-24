package com.jhughes.eznews.common.data

import android.os.Parcelable
import com.jhughes.eznews.domain.model.Article
import com.jhughes.eznews.domain.model.Source
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class ArticleParcel(
    val source: SourceParcel,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: Date
) : Parcelable

fun Article.toParcel() : ArticleParcel {
    return ArticleParcel(
        source = source.toParcel(),
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt
    )
}

fun ArticleParcel.toDomain() : Article {
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

@Parcelize
data class SourceParcel(
    val id: String?,
    val name: String?
) : Parcelable

fun Source.toParcel() : SourceParcel {
    return SourceParcel(id, name)
}

fun SourceParcel.toDomain() : Source {
    return Source(id, name)
}