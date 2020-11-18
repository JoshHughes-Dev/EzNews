package com.jhughes.eznews.headlines

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhughes.eznews.domain.ApiResult
import com.jhughes.eznews.domain.model.Article
import com.jhughes.eznews.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HeadlinesViewModel @ViewModelInject constructor(
    private val newsRepository: NewsRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val dummyData : Flow<String> = newsRepository.getDummyData()
        .flowOn(Dispatchers.IO)

    val headlinesData : MutableStateFlow<List<Article>> = MutableStateFlow(emptyList())

    fun refreshHeadlines() {
        viewModelScope.launch {
            headlinesData.value = when (val result = newsRepository.testApiData()) {
                is ApiResult.Success -> result.value
                is ApiResult.Error -> emptyList()
            }
        }
    }
}