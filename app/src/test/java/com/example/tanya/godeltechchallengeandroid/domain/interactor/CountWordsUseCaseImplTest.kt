package com.example.tanya.godeltechchallengeandroid.domain.interactor

import com.example.tanya.godeltechchallengeandroid.RxSchedulerRule
import com.example.tanya.godeltechchallengeandroid.data.DataContract
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import java.io.InputStream

class CountWordsUseCaseImplTest {

    @Rule
    @JvmField
    var testSchedulerRule = RxSchedulerRule()

    @Rule
    @JvmField
    var mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var fileRepository: DataContract.FileRepository

    @Mock
    private lateinit var wordCountRepository: DataContract.WordCountRepository

    @Mock
    private lateinit var inputStream: InputStream

    private lateinit var countWordsUseCaseImpl: CountWordsUseCaseImpl

    private val testUrl = "http://www.textfiles.com/humor/boston.geog"


    @Before
    fun onBefore() {
        countWordsUseCaseImpl = CountWordsUseCaseImpl(fileRepository, wordCountRepository)

        Mockito.`when`(fileRepository.getFileInputStream(testUrl)).thenReturn(Single.just(inputStream))
        Mockito.`when`(wordCountRepository.getWordCountsObservable(inputStream)).thenReturn(Observable.just(listOf(Pair("word1", 1))))
    }

    @Test
    fun `on countWordsUseCaseImpl execute should  `() { //TODO: create proper method name

        countWordsUseCaseImpl.execute(testUrl)
            .test()
            .assertComplete()

        verify(fileRepository).getFileInputStream(eq(testUrl))
        verifyNoMoreInteractions(fileRepository)

        verify(wordCountRepository).getWordCountsObservable(eq(inputStream))
        verifyNoMoreInteractions(wordCountRepository)
    }
}