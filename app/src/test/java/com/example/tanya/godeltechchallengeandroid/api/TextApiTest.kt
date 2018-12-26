package com.example.tanya.godeltechchallengeandroid.api

import com.example.tanya.godeltechchallengeandroid.RxSchedulerRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import java.io.InputStream

class TextApiTest {

    @JvmField
    @Rule
    val rxSchedulerRule = RxSchedulerRule()

    @JvmField
    @Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var inputStream: InputStream

    private lateinit var textApiImpl: TextApiImpl

    private val text = "The geographical center The geographical center\n" +
        "The geographical center"

    private val wordsList = mutableListOf("the", "geographical", "center", "the", "geographical", "center", "the", "geographical", "center")

    @Before
    fun onBefore() {
        textApiImpl = TextApiImpl()
    }

    @Test
    fun `on getWordsObservable should return list of expected words`() {
        textApiImpl.getWordsObservable(text.byteInputStream())
            .test()
            .assertComplete()
            //.assertValueCount(9)
            .assertValueSet(wordsList)
    }
}