package com.example.tanya.godeltechchallengeandroid.domain.interactor

import com.example.tanya.godeltechchallengeandroid.api.rest.RestService
import io.reactivex.Observable
import retrofit2.HttpException
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject

open class DownloadFileUseCase @Inject constructor(private val restService: RestService)
                        : BaseUseCase<String, String>() {

    override fun createObservable(input: String?): Observable<String> {
        return restService
            .downloadFile(input!!)
            .flatMap {
                if (it.isSuccessful) {
                    getStringFromInputStream(it.body()!!.byteStream())
                } else {
                    Observable.error(HttpException(it))
                }
            }
    }

    private fun getStringFromInputStream(inputStream: InputStream): Observable<String> =
        Observable.create<String> {
            var bufferedReader: BufferedReader? = null
            var line: String?
            val stringBuilder: StringBuilder? //TODO: to be removed probably

            try {
                bufferedReader = BufferedReader(InputStreamReader(inputStream))
                stringBuilder = StringBuilder()
                line = bufferedReader.readLine()
                stringBuilder.append(line)

                while (line != null) {
                    it.onNext(line)

                    line = bufferedReader.readLine()
                    stringBuilder.append(line)
                }

                //return stringBuilder.toString()
                it.onComplete()
            } catch (throwable: Throwable) {
                println("throwable = ${throwable.localizedMessage}")
                //return throwable.localizedMessage
                it.onError(throwable)
            } finally {
                bufferedReader?.close()
            }
        }
}