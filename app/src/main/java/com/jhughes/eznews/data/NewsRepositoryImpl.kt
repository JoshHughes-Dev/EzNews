package com.jhughes.eznews.data

import androidx.paging.*
import com.jhughes.eznews.data.remote.NewsApiService
import com.jhughes.eznews.domain.model.HeadlinesPagingKey
import com.jhughes.eznews.domain.model.Article
import com.jhughes.eznews.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val newsApiService: NewsApiService
) : NewsRepository {

    override fun topNewsHeadlines(headlinesPagingKey: HeadlinesPagingKey): Flow<PagingData<Article>> {
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
}
