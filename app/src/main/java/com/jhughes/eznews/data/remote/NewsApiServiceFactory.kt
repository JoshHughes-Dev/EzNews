package com.jhughes.eznews.data.remote

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.jhughes.eznews.BuildConfig
import com.jhughes.eznews.data.MoshiFactory
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NewsApiServiceFactory {

    fun create(context : Context, moshi: Moshi): NewsApiService {

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
            .addInterceptor(ChuckerInterceptor.Builder(context).build())
            .addInterceptor(NewsApiKeyInterceptor())
            .build()

        val restAdapter = Retrofit.Builder()
            .client(httpClient)
            .baseUrl("https://newsapi.org")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        return restAdapter.create(NewsApiService::class.java)
    }
}

class NewsApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestWithApiKey = chain.request().newBuilder()
            .header("X-Api-Key", BuildConfig.NEWS_API_KEY)
            .build()
        return chain.proceed(requestWithApiKey)
    }
}