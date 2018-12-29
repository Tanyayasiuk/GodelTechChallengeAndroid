package com.example.tanya.godeltechchallengeandroid.data

import com.example.tanya.godeltechchallengeandroid.api.ApiContract
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import java.io.InputStream
import java.util.concurrent.TimeUnit

class WordCountRepositoryTest {

    @Rule
    @JvmField
    var mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var textApi: ApiContract.TextApi

    @Mock
    private lateinit var inputStream: InputStream

    private lateinit var wordCountRepositoryImpl: WordCountRepositoryImpl

    private val wordsOfText = listOf(
        "the", "geographical", "center",
        "the", "geographical", "center",
        "the", "geographical", "center"
    )
    private val wordsOfTextObservable = Observable.create<String> { emitter ->
        wordsOfText.forEach { emitter.onNext(it) }
        emitter.onComplete()
    }
    private val words = listOf(Pair("the", 3), Pair("geographical", 3), Pair("center", 3))

    @Before
    fun onBefore() {
        whenever(textApi.getWordsObservable(eq(inputStream))).thenReturn(wordsOfTextObservable)

        wordCountRepositoryImpl = WordCountRepositoryImpl(
            textApi,
            com.example.tanya.godeltechchallengeandroid.util.TimeUnit(0, TimeUnit.SECONDS)
        )
    }

    @Test
    fun `on getWordCountsObservable call should return list of expected pairs and complete`() {
        wordCountRepositoryImpl.getWordCountsObservable(inputStream)
            .test()
            .assertValue(words)
            .assertComplete()

        verify(textApi).getWordsObservable(eq(inputStream))
        verifyNoMoreInteractions(textApi)
    }
}