package com.example.tanya.godeltechchallengeandroid.domain.interactor

import com.example.tanya.godeltechchallengeandroid.data.prefs.ApplicationRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class GetStartStatusTest {

    @Mock private lateinit var applicationRepository: ApplicationRepository
    private lateinit var getStartStatus: GetStartStatus

    private val testValue = true

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getStartStatus = GetStartStatus(applicationRepository)
    }

    @Test
    fun onGetStartStatus_shouldCallApplicationRepository() {
        Mockito.`when`(applicationRepository.isFirstStart).thenReturn(testValue)

        Assert.assertEquals(testValue, applicationRepository.isFirstStart)

        Mockito.verify(applicationRepository).isFirstStart
        Mockito.verifyNoMoreInteractions(applicationRepository)
    }



}