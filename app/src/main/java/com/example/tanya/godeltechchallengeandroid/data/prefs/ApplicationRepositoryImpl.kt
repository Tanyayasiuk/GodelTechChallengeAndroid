package com.example.tanya.godeltechchallengeandroid.data.prefs

import com.example.tanya.godeltechchallengeandroid.api.ApiContract
import com.example.tanya.godeltechchallengeandroid.data.DataContract
import javax.inject.Inject


open class ApplicationRepositoryImpl
@Inject
constructor(private val preferencesApi: ApiContract.PreferenceApi) : DataContract.ApplicationRepository {

    companion object {
        private const val IS_FIRST_START = "isFirstStart"
    }

    override var isFirstStart: Boolean
        get() = preferencesApi.getBoolean(IS_FIRST_START)
        set(value) {
            preferencesApi.setBoolean(IS_FIRST_START, value)
        }
}
