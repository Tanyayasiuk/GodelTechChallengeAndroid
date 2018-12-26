package com.example.tanya.godeltechchallengeandroid.data

import com.example.tanya.godeltechchallengeandroid.api.ApiContract
import com.example.tanya.godeltechchallengeandroid.data.prefs.ApplicationRepositoryImpl
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ApplicationRepositoryTest {

    @Mock
    private lateinit var preferenceApi: ApiContract.PreferenceApi
    private lateinit var applicationRepository: DataContract.ApplicationRepository

    private val testValue = true

    @Before
    fun onBefore() {
        MockitoAnnotations.initMocks(this)
        applicationRepository = ApplicationRepositoryImpl(preferenceApi)
    }

    @Test
    fun onIsFirstStartSet_shouldUpdatePreferenceApi() {
        applicationRepository.isFirstStart = testValue

        Mockito.verify(preferenceApi).setBoolean("isFirstStart", testValue)
        Mockito.verifyNoMoreInteractions(preferenceApi)
    }

    @Test
    fun onIsFirstStartGet_shouldReadPreferenceApi() {
        Mockito.`when`(preferenceApi.getBoolean("isFirstStart")).thenReturn(testValue)

        Assert.assertEquals(testValue, applicationRepository.isFirstStart)

        Mockito.verify(preferenceApi).getBoolean("isFirstStart")
        Mockito.verifyNoMoreInteractions(preferenceApi)
    }
}