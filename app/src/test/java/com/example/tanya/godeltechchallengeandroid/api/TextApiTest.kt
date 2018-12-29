package com.example.tanya.godeltechchallengeandroid.api

import com.example.tanya.godeltechchallengeandroid.RxSchedulerRule
import com.example.tanya.godeltechchallengeandroid.api.text.TextApiImpl
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class TextApiTest {

    @JvmField
    @Rule
    val rxSchedulerRule = RxSchedulerRule()

    @JvmField
    @Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    private lateinit var textApiImpl: TextApiImpl

    private val text = "The. geographical, \t 'center' -the geographical center \n" +
        "the geographical \"`center?"

    private val wordsList =
        listOf("the", "geographical", "center", "the", "geographical", "center", "the", "geographical", "center")

    @Before
    fun onBefore() {
        textApiImpl = TextApiImpl()
    }

    @Test
    fun `on getWordsObservable should return list of expected words`() {
        textApiImpl.getWordsObservable(text.byteInputStream())
            .test()
            .assertComplete()
            .assertValueSet(wordsList)
    }
}