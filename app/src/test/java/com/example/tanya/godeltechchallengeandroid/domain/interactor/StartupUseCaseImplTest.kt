package com.example.tanya.godeltechchallengeandroid.domain.interactor

import com.example.tanya.godeltechchallengeandroid.data.DataContract
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class StartupUseCaseImplTest {

    @Rule
    @JvmField
    var mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var applicationRepository: DataContract.ApplicationRepository
    private lateinit var startupUseCaseImpl: StartupUseCaseImpl

    @Before
    fun onBefore() {
        startupUseCaseImpl = StartupUseCaseImpl(applicationRepository)
    }

    @Test
    fun onStartup_shouldUpdateApplicationRepository() {
        startupUseCaseImpl.execute()
            .test()
            .assertComplete()
            .assertNoErrors()

        verify(applicationRepository).isFirstStart
    }
}