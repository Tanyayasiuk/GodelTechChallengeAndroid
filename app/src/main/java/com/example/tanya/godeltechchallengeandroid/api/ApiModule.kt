package com.example.tanya.godeltechchallengeandroid.api

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ApiModule {

    @Module
    companion object {
        private val PREFS_NAME = "godeltechchallengeandroid_prefs"

        @Provides @JvmStatic
        fun provideSharedPreferences(context: Context): SharedPreferences {
            return context.applicationContext.getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        }
    }

    @Binds
    abstract fun bindsPreferencesApi(preferencesApiImpl: PreferencesApiImpl): PreferenceApi
}
