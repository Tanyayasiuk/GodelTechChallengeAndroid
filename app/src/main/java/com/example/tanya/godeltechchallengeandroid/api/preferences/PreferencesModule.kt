package com.example.tanya.godeltechchallengeandroid.api.preferences

import android.content.Context
import android.content.SharedPreferences
import com.example.tanya.godeltechchallengeandroid.api.ApiContract
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [PreferencesModule.Bindings::class])
class PreferencesModule {

    private val PREFS_NAME = "godeltechchallengeandroid_prefs"

    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    @Module
    interface Bindings {

        @Binds
        fun bindsPreferencesApi(preferencesApiImpl: PreferencesApiImpl): ApiContract.PreferenceApi
    }
}