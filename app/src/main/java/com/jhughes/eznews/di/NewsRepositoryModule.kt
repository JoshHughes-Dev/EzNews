package com.jhughes.eznews.di

import android.content.Context
import com.jhughes.eznews.data.NewsRepositoryImpl
import com.jhughes.eznews.data.remote.NewsApiService
import com.jhughes.eznews.data.remote.NewsApiServiceFactory
import com.jhughes.eznews.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ApplicationComponent::class)
abstract class NewsRepositoryModule {

    @Binds
    abstract fun bindNewsRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository
}

@Module
@InstallIn(ApplicationComponent::class)
class NewsApiServiceModule {

    @Provides
    fun provideNewsApiService(@ApplicationContext context: Context): NewsApiService {
        return NewsApiServiceFactory.create(context)
    }
}