package com.jhughes.eznews.headlines

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.jhughes.eznews.domain.model.NewsCategory
import com.jhughes.eznews.domain.model.HeadlinesPagingKey
import com.jhughes.eznews.domain.model.Article
import com.jhughes.eznews.domain.model.Country
import com.jhughes.eznews.domain.repository.NewsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

class HeadlinesViewModel @ViewModelInject constructor(
    private val newsRepository: NewsRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _newsSelection : MutableStateFlow<HeadlinesPagingKey> = MutableStateFlow(
        HeadlinesPagingKey(category = NewsCategory.values().random())
    )

    val newsSelection : StateFlow<HeadlinesPagingKey> = _newsSelection

    val topHeadlines: Flow<PagingData<Article>> = flowOf(
        _newsSelection.flatMapLatest { newsRepository.topNewsHeadlines(it) }
    ).flattenMerge()

    fun setNewsCategory(newsCategory: NewsCategory) {
        _newsSelection.value = _newsSelection.value.copy(category = newsCategory)
    }

    fun setCountry(country : Country) {
        _newsSelection.value = _newsSelection.value.copy(country = country)
    }
}