package com.example.tanya.godeltechchallengeandroid.data

import com.example.tanya.godeltechchallengeandroid.api.ApiContract
import com.example.tanya.godeltechchallengeandroid.data.prefs.ApplicationRepositoryImpl
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class ApplicationRepositoryTest {

    @Rule
    @JvmField
    var mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var preferenceApi: ApiContract.PreferenceApi
    private lateinit var applicationRepository: DataContract.ApplicationRepository

    private val testValue = true

    @Before
    fun onBefore() {
        applicationRepository = ApplicationRepositoryImpl(preferenceApi)
    }

    @Test
    fun onIsFirstStartSet_shouldUpdatePreferenceApi() {
        applicationRepository.isFirstStart = testValue

        verify(preferenceApi).setBoolean("isFirstStart", testValue)
        verifyNoMoreInteractions(preferenceApi)
    }

    @Test
    fun onIsFirstStartGet_shouldReadPreferenceApi() {
        whenever(preferenceApi.getBoolean("isFirstStart")).thenReturn(testValue)

        Assert.assertEquals(testValue, applicationRepository.isFirstStart)

        verify(preferenceApi).getBoolean("isFirstStart")
        verifyNoMoreInteractions(preferenceApi)
    }
}