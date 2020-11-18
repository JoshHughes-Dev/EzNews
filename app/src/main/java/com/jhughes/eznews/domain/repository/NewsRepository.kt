package com.jhughes.eznews.domain.repository

import com.jhughes.eznews.domain.ApiResult
import com.jhughes.eznews.domain.model.Article
import kotlinx.coroutines.flow.Flow


interface NewsRepository {

    fun getDummyData() : Flow<String>

    suspend fun testApiData() : ApiResult<List<Article>>
}