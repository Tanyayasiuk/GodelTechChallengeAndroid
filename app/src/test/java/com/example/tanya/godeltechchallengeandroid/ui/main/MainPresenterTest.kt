package com.example.tanya.godeltechchallengeandroid.ui.main

import com.example.tanya.godeltechchallengeandroid.RxSchedulerRule
import com.example.tanya.godeltechchallengeandroid.domain.DomainContract
import com.example.tanya.godeltechchallengeandroid.domain.entity.Word
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Matchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit

class MainPresenterTest {

    @Rule
    @JvmField
    var testSchedulerRule = RxSchedulerRule()

    @Rule
    @JvmField
    var mockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var countWordsUseCase: DomainContract.CountWordsUseCase

    @Mock
    private lateinit var view: MainContract.View

    @Mock
    private lateinit var testWords: List<Word>

    @Mock
    private lateinit var testThrowable: Throwable

    private lateinit var presenter: MainContract.Presenter

    private lateinit var countWordsSubject: Subject<List<Word>>

    private lateinit var startButtonClickedSubject: Subject<Any>

    @Before
    fun onBefore() {
        countWordsSubject = BehaviorSubject.create()
        startButtonClickedSubject = PublishSubject.create()

        Mockito.`when`(countWordsUseCase.execute(anyString())).thenReturn(countWordsSubject)
        Mockito.`when`(view.getStartButtonClickObservable()).thenReturn(startButtonClickedSubject)
        Mockito.`when`(view.getUrlChangeObservable()).thenReturn(Observable.just(TEST_URL))

        presenter = MainPresenter(countWordsUseCase)
        presenter.bindView(view)
    }

    @After
    fun onAfter() {
        verify(view).getStartButtonClickObservable()
        verify(view).getUrlChangeObservable()
        verify(countWordsUseCase).execute(eq(TEST_URL))
        verifyNoMoreInteractions(view, countWordsUseCase)
    }

    @Test
    fun `given useCase does nothing on start button click should set Started viewState`() {
        startButtonClickedSubject.onNext(Any())
        verify(view).setViewState(eq(MainContract.ViewState.Started))
    }

    @Test
    fun `given useCase returns words on start button click should set ResultReceived viewState`() {
        countWordsSubject.onNext(testWords)
        startButtonClickedSubject.onNext(Any())
        verify(view).setViewState(eq(MainContract.ViewState.Started))
        verify(view).setViewState(MainContract.ViewState.ResultReceived(testWords))
    }

    @Test
    fun `given useCase returns throwable on start button click should set Failed viewState`() {
        countWordsSubject.onError(testThrowable)
        startButtonClickedSubject.onNext(Any())
        verify(view).setViewState(eq(MainContract.ViewState.Started))
        verify(view).setViewState(MainContract.ViewState.Failed(testThrowable))
    }

    @Test
    fun `given useCase completes should set Completed viewState`() {
        countWordsSubject.onComplete()
        startButtonClickedSubject.onNext(Any())
        verify(view).setViewState(eq(MainContract.ViewState.Started))
        verify(view).setViewState(MainContract.ViewState.Completed)
    }

    private companion object {
        private const val TEST_URL = "testUrl"
    }
}