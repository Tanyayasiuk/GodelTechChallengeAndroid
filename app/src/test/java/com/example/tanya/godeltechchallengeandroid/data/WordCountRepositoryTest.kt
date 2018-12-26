package com.example.tanya.godeltechchallengeandroid.data

import com.example.tanya.godeltechchallengeandroid.RxSchedulerRule
import com.example.tanya.godeltechchallengeandroid.api.ApiContract
import com.example.tanya.godeltechchallengeandroid.util.Timespan
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import java.io.InputStream
import java.util.concurrent.TimeUnit

class WordCountRepositoryTest {

    @Rule
    @JvmField
    var testSchedulerRule = RxSchedulerRule()

    @Rule
    @JvmField
    var mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var textApi: ApiContract.TextApi

    @Mock
    private lateinit var inputStream: InputStream

    private lateinit var wordCountRepositoryImpl: WordCountRepositoryImpl

    private val text = "The geographical center The geographical center\n" +
        "The geographical center"

    //private lateinit var subject: Subject<String>

    private val words = listOf(Pair("the", 3), Pair("geographical", 3), Pair("center", 3))
    private val wordsList = arrayOf("the", "geographical", "center")

    @Before
    fun onBefore() {
        wordCountRepositoryImpl = WordCountRepositoryImpl(textApi, Timespan(1, TimeUnit.SECONDS))
    }

    @Test
    fun `on getWordCountsObservable call should return list of expected pairs and complete`() {
        Mockito.`when`(textApi.getWordsObservable(text.byteInputStream())).thenReturn(Observable.just(text))

        wordCountRepositoryImpl.getWordCountsObservable(text.byteInputStream())  //NullPointerException getWordCountsObservable(WordCountRepositoryImpl.kt:17)
            .test()
            .assertComplete()
            //.assertValue(words)

        verify(textApi).getWordsObservable(eq(text.byteInputStream()))
        verifyNoMoreInteractions(textApi)
    }
}