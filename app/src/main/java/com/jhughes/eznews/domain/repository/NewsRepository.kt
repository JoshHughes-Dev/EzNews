package com.jhughes.eznews.domain.repository

import androidx.paging.PagingData
import com.jhughes.eznews.domain.model.Article
import com.jhughes.eznews.domain.model.NewsPagingKey
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    var selectedArticle : Article?

    fun topNewsHeadlines(headlinesPagingKey: NewsPagingKey.HeadlinesPagingKey): Flow<PagingData<Article>>

    fun allNews(everythingPagingKey: NewsPagingKey.EverythingPagingKey) : Flow<PagingData<Article>>

}