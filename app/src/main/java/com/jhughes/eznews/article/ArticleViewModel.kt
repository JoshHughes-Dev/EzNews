package com.jhughes.eznews.article

import androidx.lifecycle.ViewModel
import com.jhughes.eznews.domain.model.Article
import com.jhughes.eznews.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    var article: Article
        get() = newsRepository.selectedArticle ?: Article.empty()
        set(value) {
            newsRepository.selectedArticle = value
        }
}