package com.example.tanya.godeltechchallengeandroid.domain.interactor

import com.example.tanya.godeltechchallengeandroid.data.prefs.ApplicationRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SetStartStatusTest {

    @Mock
    private lateinit var applicationRepository: ApplicationRepository
    private lateinit var setStartStatus: SetStartStatus

    private val testValue = true

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        setStartStatus = SetStartStatus(applicationRepository)
    }

    @Test
    fun onSetStartStatus_shouldUpdateApplicationRepository() {
        setStartStatus.execute(testValue)

        Mockito.verify(applicationRepository).isFirstStart = testValue
        Mockito.verifyNoMoreInteractions(applicationRepository)
    }

}