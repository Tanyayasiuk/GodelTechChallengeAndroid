package com.example.tanya.godeltechchallengeandroid.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import android.content.SharedPreferences
import com.example.tanya.godeltechchallengeandroid.util.PREFS_NAME
import android.app.Application
import dagger.Binds
import com.example.tanya.godeltechchallengeandroid.App
import javax.inject.Singleton


@Module
abstract class AppModule {

    @Binds
    abstract fun bindApplication(app: App): Application

    @Binds
    abstract fun bindContext(application: Application): Context

    @Module
    companion object {
        @JvmStatic
        @Provides
        @Singleton
        fun provideSharedPreferences(context: Context): SharedPreferences {
            return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        }
    }
}