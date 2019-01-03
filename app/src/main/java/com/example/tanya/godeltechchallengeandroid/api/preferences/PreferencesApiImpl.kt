package com.example.tanya.godeltechchallengeandroid.api.preferences

import android.content.SharedPreferences
import com.example.tanya.godeltechchallengeandroid.api.ApiContract
import javax.inject.Inject

class PreferencesApiImpl
@Inject constructor(private val preferences: SharedPreferences) :
    ApiContract.PreferenceApi {

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return preferences.getBoolean(key, defaultValue)
    }


    override fun setBoolean(key: String, value: Boolean) {
        preferences.edit().putBoolean(key, value).apply()
    }
}