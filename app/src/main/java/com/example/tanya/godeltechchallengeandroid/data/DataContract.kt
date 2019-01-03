package com.example.tanya.godeltechchallengeandroid.data

import io.reactivex.Observable
import io.reactivex.Single
import java.io.InputStream

interface DataContract {

    interface ApplicationRepository {
        var isFirstStart: Boolean
    }

    interface FileRepository {
        fun getFileInputStream(uri: String): Single<InputStream>
    }

    interface WordCountRepository {
        fun getWordCountsObservable(inputStream: InputStream): Observable<List<Pair<String, Int>>>
    }
}