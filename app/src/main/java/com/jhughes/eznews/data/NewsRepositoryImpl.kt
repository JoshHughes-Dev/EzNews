package com.jhughes.eznews.data

import com.jhughes.eznews.data.remote.NewsRemoteDataSource
import com.jhughes.eznews.data.remote.model.HeadlinesRequest
import com.jhughes.eznews.data.remote.model.NewsCategory
import com.jhughes.eznews.data.remote.model.toDomain
import com.jhughes.eznews.domain.ApiResult
import com.jhughes.eznews.domain.model.Article
import com.jhughes.eznews.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val newsRemoteDataSource: NewsRemoteDataSource
) : NewsRepository {

    override fun getDummyData(): Flow<String> {
        return flow {
            emit("EzNews")
        }
    }

    override suspend fun testApiData(): ApiResult<List<Article>> = withContext(Dispatchers.IO) {
        val apiResult = newsRemoteDataSource.getTopHeadlines(
            HeadlinesRequest(
                country = "gb",
                category = NewsCategory.TECHNOLOGY,
            )
        )

        when (apiResult) {
            is ApiResult.Success -> ApiResult.Success(apiResult.value.articles.map { it.toDomain() })
            is ApiResult.Error -> apiResult
        }
    }
}