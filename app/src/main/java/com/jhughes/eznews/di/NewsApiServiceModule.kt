package com.jhughes.eznews.di

import android.content.Context
import com.jhughes.eznews.data.remote.NewsApiService
import com.jhughes.eznews.data.remote.NewsApiServiceFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ApplicationComponent::class)
class NewsApiServiceModule {

    @Provides
    fun provideNewsApiService(@ApplicationContext context: Context): NewsApiService {
        return NewsApiServiceFactory.create(context)
    }
}