package com.jhughes.eznews.di

import android.content.Context
import com.jhughes.eznews.data.MoshiFactory
import com.jhughes.eznews.data.remote.NewsApiService
import com.jhughes.eznews.data.remote.NewsApiServiceFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NewsApiServiceModule {

    @Provides
    @Singleton
    fun provideMoshi() : Moshi {
        return MoshiFactory.create()
    }

    @Provides
    fun provideNewsApiService(@ApplicationContext context: Context, moshi : Moshi): NewsApiService {
        return NewsApiServiceFactory.create(context, moshi)
    }
}