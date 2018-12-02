package com.example.tanya.godeltechchallengeandroid.data.prefs

import android.content.Context
import android.content.Context.MODE_PRIVATE
import javax.inject.Inject

/**
 * Storage for app preferences.
 */
interface PreferenceStorage {
    var isFirstStart: Boolean
}

/**
 * [PreferenceStorage] impl
 */
class ApplicationPreferences
@Inject
constructor(context: Context): PreferenceStorage {

    companion object {
        private val PREFS_NAME = "godeltechchallengeandroid_prefs"
        private val IS_FIRST_START = "isFirstStart"
    }

    private val prefs = context.applicationContext.getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

    override var isFirstStart: Boolean
        get() = prefs.getBoolean(IS_FIRST_START, true)
        set(value) = prefs.edit().putBoolean(IS_FIRST_START, value).apply()
}
