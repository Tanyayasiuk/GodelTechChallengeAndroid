package com.example.tanya.godeltechchallengeandroid.api

import io.reactivex.Observable
import java.io.InputStream

interface ApiContract {

    interface PreferenceApi {

        fun getBoolean(key: String, defaultValue: Boolean = false): Boolean
        fun setBoolean(key: String, value: Boolean)
    }

    interface TextApi {
        fun getWordsObservable(inputStream: InputStream): Observable<String>
    }
}