package com.jhughes.eznews.headlines

import androidx.paging.PagingData
import com.jhughes.eznews.CoroutineTestRule
import com.jhughes.eznews.domain.model.Article
import com.jhughes.eznews.domain.repository.NewsRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HeadlinesViewModelTest {

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    @MockK
    lateinit var newsRepository: NewsRepository

    private lateinit var viewModel : HeadlinesViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = HeadlinesViewModel(newsRepository, mockk())
    }

    @Test
    fun topHeadlines() = coroutineTestRule.runBlockingTest {
        val pagingData = mockk<PagingData<Article>>()

        every { newsRepository.topNewsHeadlines(any()) } returns flow { pagingData }

        viewModel.topHeadlines.collect {
            assert(it == pagingData)
        }

        //todo
    }

    @Test
    fun setNewsCategory() = coroutineTestRule.runBlockingTest {
        //todo
    }

    @Test
    fun setCountry() {
        //todo
    }
}