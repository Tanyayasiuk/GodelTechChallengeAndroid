package com.example.tanya.godeltechchallengeandroid.api.preferences

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides

@Module
class PreferencesModule {

    private val PREFS_NAME = "godeltechchallengeandroid_prefs"

    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }
}