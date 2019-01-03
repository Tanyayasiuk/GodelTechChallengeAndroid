package com.example.tanya.godeltechchallengeandroid.data

import com.example.tanya.godeltechchallengeandroid.api.rest.RestService
import io.reactivex.Single
import retrofit2.HttpException
import java.io.InputStream
import javax.inject.Inject

class FileRepositoryImpl @Inject constructor(private val restService: RestService) : DataContract.FileRepository {

    override fun getFileInputStream(uri: String): Single<InputStream> {
        return restService
            .downloadFile(uri)
            .flatMap { response ->
                if (response.isSuccessful) {
                    Single.just(response.body()!!.byteStream())
                } else {
                    Single.error(HttpException(response))
                }
            }
    }
}