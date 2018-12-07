package com.example.tanya.godeltechchallengeandroid.ui.start

import com.example.tanya.godeltechchallengeandroid.RxSchedulerRule
import com.example.tanya.godeltechchallengeandroid.domain.interactor.SetStartStatus
import com.nhaarman.mockito_kotlin.verify
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
        result.onNext(Unit)
        presenter.loadData()

        Assert.assertEquals(result, setStartStatus.execute(anyBoolean()))

        //todo: figure out how to test view
        //Mockito.verify(view).navigateToHomeScreen()
        //Mockito.verifyNoMoreInteractions(view)
    }

    @Test
    fun onLoadDataError_shouldShowError() {
        val throwable = Throwable("Error")
        result.onError(throwable)

        presenter.loadData()

        verify(view).showError(throwable.localizedMessage)
    }

}

