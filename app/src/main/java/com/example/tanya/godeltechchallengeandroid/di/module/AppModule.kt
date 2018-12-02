package com.example.tanya.godeltechchallengeandroid.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import android.app.Application
import dagger.Binds
import com.example.tanya.godeltechchallengeandroid.App
import com.example.tanya.godeltechchallengeandroid.data.prefs.ApplicationPreferences
import com.example.tanya.godeltechchallengeandroid.data.prefs.PreferenceStorage
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
        fun providesPreferenceStorage(context: Context): PreferenceStorage =
            ApplicationPreferences(context)
    }

}