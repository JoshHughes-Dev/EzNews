package com.jhughes.eznews.domain.repository

import androidx.paging.PagingData
import com.jhughes.eznews.domain.model.HeadlinesPagingKey
import com.jhughes.eznews.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun topNewsHeadlines(headlinesPagingKey: HeadlinesPagingKey): Flow<PagingData<Article>>
}