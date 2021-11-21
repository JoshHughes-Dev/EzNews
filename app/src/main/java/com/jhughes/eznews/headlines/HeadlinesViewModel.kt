package com.jhughes.eznews.headlines

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jhughes.eznews.domain.model.Article
import com.jhughes.eznews.domain.model.Country
import com.jhughes.eznews.domain.model.HeadlinesPagingKey
import com.jhughes.eznews.domain.model.NewsCategory
import com.jhughes.eznews.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class HeadlinesViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val newsRepository: NewsRepository
) : ViewModel() {

    init {
        Log.d("Headlines", "viewModel created. $this")
    }

    private val _newsSelection : MutableStateFlow<HeadlinesPagingKey> = MutableStateFlow(
        HeadlinesPagingKey(category = NewsCategory.values().random())
    )

    val newsSelection : StateFlow<HeadlinesPagingKey> = _newsSelection

    var selectedArticle : Article = Article.empty()

    val isRefreshing : MutableStateFlow<Boolean> = MutableStateFlow(false)

    @OptIn(ExperimentalCoroutinesApi::class)
    val topHeadlines: Flow<PagingData<Article>> = _newsSelection.flatMapLatest {
        newsRepository.topNewsHeadlines(it)
    }.cachedIn(viewModelScope)

    fun setFilters(newsCategory: NewsCategory, country : Country) {
        _newsSelection.value = _newsSelection.value.copy(category = newsCategory, country = country)
    }
}