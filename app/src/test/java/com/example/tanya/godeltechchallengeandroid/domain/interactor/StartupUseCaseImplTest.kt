package com.example.tanya.godeltechchallengeandroid.domain.interactor

import com.example.tanya.godeltechchallengeandroid.data.DataContract
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class StartupUseCaseImplTest {

    @Mock
    private lateinit var applicationRepository: DataContract.ApplicationRepository
    private lateinit var startupUseCaseImpl: StartupUseCaseImpl

    @Before
    fun onBefore() {
        MockitoAnnotations.initMocks(this)
        startupUseCaseImpl = StartupUseCaseImpl(applicationRepository)
    }

    @Test
    fun onStartup_shouldUpdateApplicationRepository() {
        startupUseCaseImpl.execute()
            .test()
            .assertComplete()
            .assertNoErrors()

        Mockito.verify(applicationRepository).isFirstStart
    }
}