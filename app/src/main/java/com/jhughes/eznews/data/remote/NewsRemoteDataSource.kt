package com.jhughes.eznews.data.remote

import com.jhughes.eznews.data.remote.model.HeadlinesRequest
import com.jhughes.eznews.data.remote.model.HeadlinesResponse
import com.jhughes.eznews.domain.ApiResult
import okio.IOException
import java.util.*
import javax.inject.Inject

class NewsRemoteDataSource @Inject constructor(
    private val newsApiService: NewsApiService
) {

    suspend fun getTopHeadlines(requestParams: HeadlinesRequest): ApiResult<HeadlinesResponse> {
        return try {
            val headlinesResponse = newsApiService.getTopHeadlines(
                country = requestParams.country,
                category = requestParams.category?.name?.toLowerCase(Locale.ENGLISH),
                pageSize = requestParams.pageSize,
                page = requestParams.page
            )
            if (headlinesResponse.status == "error") {
                throw IOException() // todo move into moshi converter + use explicit exception
            }
            ApiResult.Success(headlinesResponse)
        } catch (e: Exception) {
            ApiResult.Error(e)
        }
    }
}