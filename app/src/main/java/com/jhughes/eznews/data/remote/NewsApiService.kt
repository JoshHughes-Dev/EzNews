package com.jhughes.eznews.data.remote

import com.jhughes.eznews.data.remote.model.response.HeadlinesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("/v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String?,
        @Query("category") category: String?,
        @Query("q", encoded = true) query: String?,
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int
    ): HeadlinesResponse
}