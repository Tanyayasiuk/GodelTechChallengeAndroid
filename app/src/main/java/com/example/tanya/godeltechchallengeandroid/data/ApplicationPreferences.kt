package com.example.tanya.godeltechchallengeandroid.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import javax.inject.Inject

class ApplicationPreferences
@Inject
constructor(context: Context) {

    companion object {
        private val PREFS_NAME = "godeltechchallengeandroid_prefs"
        private val IS_FIRST_START = "isFirstStart"
    }

    private val prefs = context.applicationContext.getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

    var isFirstStart: Boolean
        get() = prefs.getBoolean(IS_FIRST_START, true)
        set(value) = prefs.edit().putBoolean(IS_FIRST_START, value).apply()
}
