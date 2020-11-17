package com.jhughes.eznews.headlines

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.jhughes.eznews.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class HeadlinesViewModel @ViewModelInject constructor(
    private val newsRepository: NewsRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val dummyData : Flow<String> = newsRepository.getDummyData()
        .flowOn(Dispatchers.IO)

}