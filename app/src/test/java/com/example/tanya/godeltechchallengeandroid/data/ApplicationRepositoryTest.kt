package com.example.tanya.godeltechchallengeandroid.data

import com.example.tanya.godeltechchallengeandroid.api.PreferenceApi
import com.example.tanya.godeltechchallengeandroid.data.prefs.ApplicationRepository
import com.example.tanya.godeltechchallengeandroid.data.prefs.ApplicationRepositoryImpl
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ApplicationRepositoryTest {

    @Mock
    private lateinit var preferenceApi: PreferenceApi
    private lateinit var applicationRepository: ApplicationRepository

    private val testValue = true

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        applicationRepository = ApplicationRepositoryImpl(preferenceApi)
    }

    @Test
    fun onIsFirstStartSet_shouldPreferenceApiUpdate() {
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