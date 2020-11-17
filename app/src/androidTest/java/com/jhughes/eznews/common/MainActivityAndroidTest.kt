package com.jhughes.eznews.common

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class MainActivityAndroidTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = AndroidComposeTestRule(ActivityScenarioRule(MainActivity::class.java))

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun qwerty() {
        //todo
    }
}