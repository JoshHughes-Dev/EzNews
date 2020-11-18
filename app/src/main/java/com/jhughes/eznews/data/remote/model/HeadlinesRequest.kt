package com.jhughes.eznews.data.remote.model

data class HeadlinesRequest(
    val country: String? = null,
    val category: NewsCategory? = null,
    val pageSize: Int = 20,
    val page: Int = 1
)