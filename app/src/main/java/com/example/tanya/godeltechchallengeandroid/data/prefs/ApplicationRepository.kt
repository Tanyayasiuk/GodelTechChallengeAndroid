package com.example.tanya.godeltechchallengeandroid.data.prefs

import com.example.tanya.godeltechchallengeandroid.api.PreferenceApi
import javax.inject.Inject

/**
 * Storage for app preferences.
 */
interface ApplicationRepository {
    var isFirstStart: Boolean
}

/**
 * [ApplicationRepository] impl
 */
open class ApplicationRepositoryImpl
@Inject
constructor(private val preferencesApi: PreferenceApi): ApplicationRepository {

    companion object {
        private val IS_FIRST_START = "isFirstStart"
    }

    override var isFirstStart: Boolean
        get() = preferencesApi.getBoolean(IS_FIRST_START)
        set(value) {preferencesApi.setBoolean(IS_FIRST_START, value)}
}
