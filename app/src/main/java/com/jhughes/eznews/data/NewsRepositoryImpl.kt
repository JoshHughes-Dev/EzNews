package com.jhughes.eznews.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jhughes.eznews.data.remote.NewsApiService
import com.jhughes.eznews.domain.model.Article
import com.jhughes.eznews.domain.model.NewsPagingKey
import com.jhughes.eznews.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val newsApiService: NewsApiService
) : NewsRepository {

    override var selectedArticle: Article? = null

    override fun topNewsHeadlines(headlinesPagingKey: NewsPagingKey.HeadlinesPagingKey): Flow<PagingData<Article>> {
        return Pager(
            PagingConfig(
                pageSize = headlinesPagingKey.pageSize,
                prefetchDistance = headlinesPagingKey.pageSize.div(4)
            )
        ) {
            PageKeyedHeadlinesPagingSource(
                initialKey = headlinesPagingKey,
                newsApiService = newsApiService
            )
        }.flow
    }

    override fun allNews(everythingPagingKey: NewsPagingKey.EverythingPagingKey): Flow<PagingData<Article>> {
        return Pager(
            PagingConfig(
                pageSize = everythingPagingKey.pageSize,
                prefetchDistance = everythingPagingKey.pageSize.div(4)
            )
        ) {
            PageKeyedEverythingPagingSource(
                initialKey = everythingPagingKey,
                newsApiService = newsApiService
            )
        }.flow
    }
}
