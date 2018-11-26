package com.example.tanya.godeltechchallengeandroid.di.module

import android.content.Context
import com.example.tanya.godeltechchallengeandroid.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import android.content.SharedPreferences
import com.example.tanya.godeltechchallengeandroid.util.PREFS_NAME


@Module
class AppModule {
    @Provides
    @Singleton
    fun provideApplication(app : App): Context = app

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }
}