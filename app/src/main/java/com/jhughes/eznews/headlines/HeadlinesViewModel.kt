package com.jhughes.eznews.headlines

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.jhughes.eznews.domain.model.NewsCategory
import com.jhughes.eznews.domain.model.HeadlinesPagingKey
import com.jhughes.eznews.domain.model.Article
import com.jhughes.eznews.domain.repository.NewsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

class HeadlinesViewModel @ViewModelInject constructor(
    private val newsRepository: NewsRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val newsSelection: MutableStateFlow<HeadlinesPagingKey> = MutableStateFlow(HeadlinesPagingKey())

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val topHeadlines: Flow<PagingData<Article>> = flowOf(
        newsSelection.flatMapLatest { newsRepository.topNewsHeadlines(it) }
    ).flattenMerge()

    fun randomCategory() {
        newsSelection.value = HeadlinesPagingKey(
            category = NewsCategory.values().toList().random()
        )
    }
}