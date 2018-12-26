package com.example.tanya.godeltechchallengeandroid.api

import io.reactivex.Observable
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.regex.Pattern
import javax.inject.Inject

class TextApiImpl @Inject constructor() : ApiContract.TextApi {

    override fun getWordsObservable(inputStream: InputStream): Observable<String> {
        return Observable.create { emitter ->
            try {
                BufferedReader(InputStreamReader(inputStream)).use { bufferedReader ->
                    fun readLine() = bufferedReader.readLine()?.toString()

                    var line = readLine()

                    while (line != null && !emitter.isDisposed) {
                        val matcher = PATTERN.matcher(line)

                        while (matcher.find()) {
                            emitter.onNext(matcher.group().toLowerCase())
                        }

                        line = readLine()
                    }

                    if (!emitter.isDisposed) {
                        emitter.onComplete()
                    }
                }
            } catch (throwable: Throwable) {
                if (!emitter.isDisposed) {
                    emitter.onError(throwable)
                }
            }
        }
    }

    private companion object {
        private val PATTERN = Pattern.compile("(\\w+'\\w+)|(\\w+-\\w+)|(\\w+)")
    }
}