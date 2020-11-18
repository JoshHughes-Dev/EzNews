package com.jhughes.eznews.data

import com.jhughes.eznews.CoroutineTestRule
import com.jhughes.eznews.data.remote.NewsApiService
import com.jhughes.eznews.domain.model.HeadlinesPagingKey
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PageKeyedHeadlinesPagingSourceTest {

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    @MockK
    lateinit var newsApiService: NewsApiService

    private lateinit var pagingSource: PageKeyedHeadlinesPagingSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        pagingSource = PageKeyedHeadlinesPagingSource(
            initialKey = HeadlinesPagingKey(),
            newsApiService = newsApiService
        )
    }

    @Test
    fun qwerty() = coroutineTestRule.runBlockingTest {
        //todo
    }
}