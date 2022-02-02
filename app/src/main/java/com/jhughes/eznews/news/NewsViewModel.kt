package com.jhughes.eznews.news

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jhughes.eznews.domain.model.Article
import com.jhughes.eznews.domain.model.NewsCategory
import com.jhughes.eznews.domain.model.NewsPagingKey
import com.jhughes.eznews.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val newsRepository: NewsRepository
) : ViewModel() {

    init {
        Log.d("Headlines", "viewModel created. $this")
    }

    private val _newsSelection: MutableStateFlow<NewsPagingKey.HeadlinesPagingKey> = MutableStateFlow(
        NewsPagingKey.HeadlinesPagingKey(category = NewsCategory.values().random())
    )

    val newsSelection: StateFlow<NewsPagingKey.HeadlinesPagingKey> = _newsSelection

    var selectedArticle: Article
        get() = newsRepository.selectedArticle ?: Article.empty()
        set(value) {
            newsRepository.selectedArticle = value
        }

    val isRefreshing: MutableStateFlow<Boolean> = MutableStateFlow(false)

    @OptIn(ExperimentalCoroutinesApi::class)
    val newsSearchResults: Flow<PagingData<Article>> =
        _newsSelection.flatMapLatest { newsSearchFilters ->
            newsRepository.topNewsHeadlines(newsSearchFilters)
        }.cachedIn(viewModelScope)

    fun setFilters(newsSelection: NewsPagingKey.HeadlinesPagingKey) {
        _newsSelection.value = newsSelection
    }
}