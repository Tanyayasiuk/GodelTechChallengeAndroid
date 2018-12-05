package com.example.tanya.godeltechchallengeandroid.api

interface PreferenceApi {

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean
    fun setBoolean(key: String, value: Boolean)
}