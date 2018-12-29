package com.example.tanya.godeltechchallengeandroid.ui.core

import com.example.tanya.godeltechchallengeandroid.api.rest.RestService
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import okhttp3.ResponseBody
import okio.BufferedSource
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import retrofit2.Response
import javax.inject.Inject

class ApiMocksController @Inject constructor(private val restService: RestService) {

    fun onBefore() {
        Mockito.reset(restService)
    }

    fun givenRestServiceReturnsNothing() {
        whenever(restService.downloadFile(anyString())).thenReturn(Single.create<Response<ResponseBody>> {
            //does nothing
        })
    }

    fun givenRestServiceReturnsError(throwable: Throwable) {
        whenever(restService.downloadFile(anyString())).thenReturn(Single.error(throwable))
    }

    fun givenRestServiceReturnsResult(string: String) {
        val responseBody = mock<ResponseBody>()
        val bufferedSource = mock<BufferedSource>()
        val result = Single.just(Response.success(responseBody))

        whenever(responseBody.source()).thenReturn(bufferedSource)
        whenever(bufferedSource.inputStream()).thenReturn(string.byteInputStream())
        whenever(restService.downloadFile(anyString())).thenReturn(result)
    }
}