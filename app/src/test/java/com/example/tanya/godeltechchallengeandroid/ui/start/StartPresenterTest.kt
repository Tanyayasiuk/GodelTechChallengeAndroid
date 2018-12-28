package com.example.tanya.godeltechchallengeandroid.ui.start

import com.example.tanya.godeltechchallengeandroid.RxSchedulerRule
import com.example.tanya.godeltechchallengeandroid.domain.interactor.StartupUseCaseImpl
import com.example.tanya.godeltechchallengeandroid.util.TimeUnit
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.subjects.PublishSubject
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule


class StartPresenterTest {

    @Rule
    @JvmField
    var testSchedulerRule = RxSchedulerRule()

    @Rule
    @JvmField
    var mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var startupUseCaseImpl: StartupUseCaseImpl
    @Mock
    private lateinit var view: StartContract.View

    private lateinit var presenter: StartContract.Presenter
    private var result = PublishSubject.create<Unit>()


    @Before
    fun onBefore() {
        presenter = StartPresenter(startupUseCaseImpl, TimeUnit(5000, java.util.concurrent.TimeUnit.MILLISECONDS))
        whenever(startupUseCaseImpl.execute()).thenReturn(result)
    }

    @Test
    fun onLoadDataSuccess_shouldNavigateToHomeScreen() {
        presenter.bindView(view)
        result.onNext(Unit)

        verify(startupUseCaseImpl).execute()
        verifyNoMoreInteractions(startupUseCaseImpl)

        verify(view).navigateToHomeScreen()
        verifyNoMoreInteractions(view)
    }

    @Test
    fun onLoadDataError_shouldShowError() {
        val throwable = Throwable("Error")

        presenter.bindView(view)
        result.onError(throwable)

        verify(startupUseCaseImpl).execute()
        verifyNoMoreInteractions(startupUseCaseImpl)

        verify(view).showError(throwable.localizedMessage)
        verifyNoMoreInteractions(view)
    }
}

