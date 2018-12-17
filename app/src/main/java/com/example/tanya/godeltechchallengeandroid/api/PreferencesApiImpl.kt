package com.example.tanya.godeltechchallengeandroid.api

import android.content.SharedPreferences
import javax.inject.Inject

class PreferencesApiImpl
@Inject constructor(private val preferences: SharedPreferences): PreferenceApi{

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return preferences.getBoolean(key, defaultValue)
    }


    override fun setBoolean(key: String, value: Boolean) {
        preferences.edit().putBoolean(key, value).apply()
    }
}