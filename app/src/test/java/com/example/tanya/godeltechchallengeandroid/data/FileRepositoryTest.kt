package com.example.tanya.godeltechchallengeandroid.data

import com.example.tanya.godeltechchallengeandroid.RxSchedulerRule
import com.example.tanya.godeltechchallengeandroid.api.rest.RestService
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import okhttp3.ResponseBody
import okio.BufferedSource
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import retrofit2.HttpException
import retrofit2.Response
import java.io.InputStream

class FileRepositoryTest {

    @Rule
    @JvmField
    var testSchedulerRule = RxSchedulerRule()

    @Rule
    @JvmField
    var mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var responseBody: ResponseBody

    @Mock
    private lateinit var inputStream: InputStream

    @Mock
    private lateinit var bufferedSource: BufferedSource

    @Mock
    private lateinit var restService: RestService

    private lateinit var fileRepository: DataContract.FileRepository

    private val testUri = "http://www.textfiles.com/humor/boston.geog"

    @Before
    fun onBefore() {
        fileRepository = FileRepositoryImpl(restService)
    }

    @Test
    fun `given restService returns success on getFileInputStream method call it should complete`() {
        whenever(responseBody.source()).thenReturn(bufferedSource)
        whenever(bufferedSource.inputStream()).thenReturn(inputStream)
        whenever(restService.downloadFile(testUri))
            .thenReturn(Single.just(Response.success(responseBody)))

        fileRepository.getFileInputStream(testUri)
            .test()
            .assertComplete()

        verify(restService).downloadFile(eq(testUri))
        verifyNoMoreInteractions(restService)
    }

    @Test
    fun `given restService returns error on getFileInputStream method call it should complete with error`() {
        whenever(restService.downloadFile(testUri))
            .thenReturn(Single.just(Response.error<ResponseBody>(400, responseBody)))

        fileRepository.getFileInputStream(testUri)
            .test()
            .assertError(HttpException::class.java)

        verify(restService).downloadFile(eq(testUri))
        verifyNoMoreInteractions(restService)
    }
}
