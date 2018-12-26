package com.example.tanya.godeltechchallengeandroid.api.rest

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url


interface RestService {

    @Streaming
    @GET
    fun downloadFile(@Url url: String): Single<Response<ResponseBody>>
}