package com.jhughes.eznews.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jhughes.eznews.data.remote.NewsApiService
import com.jhughes.eznews.data.remote.model.toDomain
import com.jhughes.eznews.data.remote.model.toQueryParamValue
import com.jhughes.eznews.domain.model.Article
import com.jhughes.eznews.domain.model.NewsPagingKey

class PageKeyedHeadlinesPagingSource(
    private val initialKey: NewsPagingKey.HeadlinesPagingKey,
    private val newsApiService: NewsApiService
) : PagingSource<NewsPagingKey.HeadlinesPagingKey, Article>() {
    override suspend fun load(params: LoadParams<NewsPagingKey.HeadlinesPagingKey>): LoadResult<NewsPagingKey.HeadlinesPagingKey, Article> {
        return try {
            val requestParam = params.key ?: initialKey

            val data = newsApiService.getTopHeadlines(
                country = requestParam.country.countryCode,
                category = requestParam.category.toQueryParamValue(),
                query = requestParam.keyword,
                pageSize = requestParam.pageSize,
                page = requestParam.page
            )

            LoadResult.Page(
                data = data.articles.map { it.toDomain() },
                prevKey = null,
                nextKey = createNextKey(requestParam, data.totalResults),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private fun createNextKey(
        currentKey: NewsPagingKey.HeadlinesPagingKey,
        totalResults: Int
    ): NewsPagingKey.HeadlinesPagingKey? {
        val currentMaxResults = currentKey.pageSize * currentKey.page

        return if (currentMaxResults < totalResults) {
            currentKey.copy(page = currentKey.page + 1)
        } else {
            null
        }
    }

    override fun getRefreshKey(state: PagingState<NewsPagingKey.HeadlinesPagingKey, Article>): NewsPagingKey.HeadlinesPagingKey? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.run { copy(page = page.plus(1)) }
                ?: anchorPage?.nextKey?.run { copy(page = page.minus(1)) }
        }
    }
}

