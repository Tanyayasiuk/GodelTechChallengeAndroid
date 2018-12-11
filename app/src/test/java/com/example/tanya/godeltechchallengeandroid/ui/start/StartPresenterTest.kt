package com.example.tanya.godeltechchallengeandroid.ui.start

import com.example.tanya.godeltechchallengeandroid.RxSchedulerRule
import com.example.tanya.godeltechchallengeandroid.domain.interactor.SetStartStatus
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import io.reactivex.subjects.PublishSubject
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class StartPresenterTest {

    @Rule
    @JvmField var testSchedulerRule = RxSchedulerRule()

    @Mock
    private lateinit var setStartStatus: SetStartStatus
    @Mock
    private lateinit var view: StartContract.View

    private lateinit var presenter: StartContract.Presenter
    private var result = PublishSubject.create<Unit>()


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = StartPresenter(setStartStatus)
        presenter.bindView(view)
        Mockito.`when`(setStartStatus.execute(anyBoolean())).thenReturn(result)
    }

    @Test
    fun onLoadDataSuccess_shouldNavigateToHomeScreen() {
        presenter.loadData()
        result.onNext(Unit)

        Assert.assertEquals(result, setStartStatus.execute(anyBoolean()))

        verify(view).navigateToHomeScreen()
        verifyNoMoreInteractions(view)
    }

    @Test
    fun onLoadDataError_shouldShowError() {
        val throwable = Throwable("Error")

        presenter.loadData()
        result.onError(throwable)

        verify(view).showError(throwable.localizedMessage)
    }

}

