package com.example.tanya.godeltechchallengeandroid.domain.interactor

import com.example.tanya.godeltechchallengeandroid.data.prefs.ApplicationRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class StartupUseCaseTest {

    @Mock
    private lateinit var applicationRepository: ApplicationRepository
    private lateinit var startupUseCase: StartupUseCase

    @Before
    fun onBefore() {
        MockitoAnnotations.initMocks(this)
        startupUseCase = StartupUseCase(applicationRepository)
    }

    @Test
    fun onStartup_shouldUpdateApplicationRepository() {
        startupUseCase.execute()
            .test()
            .assertComplete()
            .assertNoErrors()

        Mockito.verify(applicationRepository).isFirstStart
    }
}