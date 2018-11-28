package com.example.tanya.godeltechchallengeandroid.data

import android.content.SharedPreferences
import javax.inject.Inject

const val PREFS_NAME = "godeltechchallengeandroid_prefs"

class ApplicationPreferences
@Inject
constructor(private var prefs: SharedPreferences) {

    private val IS_FIRST_START = "isFirstStart"

    var isFirstStart: Boolean
        get() = prefs.getBoolean(IS_FIRST_START, true)
        set(value) = prefs.edit().putBoolean(IS_FIRST_START, value).apply()
}
