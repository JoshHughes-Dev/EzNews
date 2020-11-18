package com.jhughes.eznews.data

import com.jhughes.eznews.CoroutineTestRule
import com.jhughes.eznews.data.remote.NewsApiService
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NewRepositoryImplTest {

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    @MockK
    lateinit var newsApiService: NewsApiService

    private lateinit var repository : NewsRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = NewsRepositoryImpl(newsApiService)
    }

    @Test
    fun qwerty() = coroutineTestRule.runBlockingTest {
        //todo
    }
}