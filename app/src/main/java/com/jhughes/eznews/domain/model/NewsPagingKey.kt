package com.jhughes.eznews.domain.model


sealed class NewsPagingKey {

    data class HeadlinesPagingKey(
        val country: Country = Country.UNITED_KINGDOM,
        val category: NewsCategory = NewsCategory.ALL,
        val keyword: String? = null,
        val pageSize: Int = 20,
        val page: Int = 1
    ) : NewsPagingKey()

    data class EverythingPagingKey(
        val country: Country = Country.UNITED_KINGDOM,
        val keyword: String? = null,
        val pageSize: Int = 20,
        val page: Int = 1
    ) : NewsPagingKey()
}