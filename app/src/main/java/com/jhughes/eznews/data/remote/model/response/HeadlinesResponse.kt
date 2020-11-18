package com.jhughes.eznews.data.remote.model.response

import com.jhughes.eznews.data.remote.model.NewsArticle
import com.squareup.moshi.Json

data class HeadlinesResponse(
    @field:Json(name = "status") val status : String,
    @field:Json(name ="code") val errorCode : String? = null,
    @field:Json(name ="message") val errorMessage : String? = null,
    @field:Json(name ="totalResults") val totalResults : Int,
    @field:Json(name ="articles") val articles : List<NewsArticle>
)