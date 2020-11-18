package com.jhughes.eznews.domain.model

data class HeadlinesPagingKey(
    val country: String = "gb",
    val category: NewsCategory = NewsCategory.ALL,
    val pageSize: Int = 20,
    val page: Int = 1
)