package com.example.tanya.godeltechchallengeandroid.ui.start

import com.example.tanya.godeltechchallengeandroid.RxSchedulerRule
import com.example.tanya.godeltechchallengeandroid.domain.interactor.StartupUseCase
import com.example.tanya.godeltechchallengeandroid.util.TimeUnit
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import io.reactivex.subjects.PublishSubject
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class StartPresenterTest {

    @Rule
    @JvmField
    var testSchedulerRule = RxSchedulerRule()

    @Mock
    private lateinit var startupUseCase: StartupUseCase
    @Mock
    private lateinit var view: StartContract.View

    private lateinit var presenter: StartContract.Presenter
    private var result = PublishSubject.create<Unit>()


    @Before
    fun onBefore() {
        MockitoAnnotations.initMocks(this)
        presenter = StartPresenter(startupUseCase, TimeUnit(5000, java.util.concurrent.TimeUnit.MILLISECONDS))
        Mockito.`when`(startupUseCase.execute()).thenReturn(result)
    }

    @Test
    fun onLoadDataSuccess_shouldNavigateToHomeScreen() {
        presenter.bindView(view)
        result.onNext(Unit)

        verify(startupUseCase).execute()
        verifyNoMoreInteractions(startupUseCase)

        verify(view).navigateToHomeScreen()
        verifyNoMoreInteractions(view)
    }

    @Test
    fun onLoadDataError_shouldShowError() {
        val throwable = Throwable("Error")

        presenter.bindView(view)
        result.onError(throwable)

        verify(startupUseCase).execute()
        verifyNoMoreInteractions(startupUseCase)

        verify(view).showError(throwable.localizedMessage)
        verifyNoMoreInteractions(view)
    }
}

