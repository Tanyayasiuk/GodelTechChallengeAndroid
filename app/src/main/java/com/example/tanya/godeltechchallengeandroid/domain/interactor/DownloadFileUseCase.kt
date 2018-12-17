package com.example.tanya.godeltechchallengeandroid.domain.interactor

import com.example.tanya.godeltechchallengeandroid.api.rest.RestService
import io.reactivex.Observable
import retrofit2.HttpException
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject

//TODO: specify the return type
class DownloadFileUseCase @Inject constructor(private val restService: RestService)
                        : BaseUseCase<String, String>() {

    override fun createObservable(input: String?): Observable<String> {
        return restService
            .downloadFile(input!!)
            .flatMap {
                if (it.isSuccessful) {
                    Observable.just(stringFromInputStream(it.body()!!.byteStream()))
                } else {
                    Observable.error(HttpException(it))
                }
            }
    }

    private fun stringFromInputStream(inputStream: InputStream): String {
        var bufferedReader: BufferedReader? = null
        var line: String?
        val stringBuilder: StringBuilder?

        try {
            bufferedReader = BufferedReader(InputStreamReader(inputStream))
            stringBuilder = StringBuilder()
            line = bufferedReader.readLine()
            stringBuilder.append(line)

            while (line != null) {
                line = bufferedReader.readLine()
                stringBuilder.append(line)
            }

            return stringBuilder.toString()

        } catch (throwable: Throwable) {
            println("throwable = ${throwable.localizedMessage}")
            return throwable.localizedMessage
        } finally {
            bufferedReader?.close()
        }
    }
}