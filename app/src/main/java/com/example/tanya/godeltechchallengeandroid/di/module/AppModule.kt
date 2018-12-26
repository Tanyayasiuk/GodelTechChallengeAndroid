package com.example.tanya.godeltechchallengeandroid.di.module

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.example.tanya.godeltechchallengeandroid.App
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module
abstract class AppModule {

    @Binds
    abstract fun bindApplication(app: App): Application

    @Binds
    abstract fun bindContext(application: Application): Context

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideResources(context: Context): Resources =
            context.resources
    }
}