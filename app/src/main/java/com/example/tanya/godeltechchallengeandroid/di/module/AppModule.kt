package com.example.tanya.godeltechchallengeandroid.di.module

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.example.tanya.godeltechchallengeandroid.App
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [AppModule.Bindings::class])
class AppModule {

    @Provides
    fun provideResources(context: Context): Resources =
        context.resources

    @Module
    interface Bindings {
        @Binds
        fun bindApplication(app: App): Application

        @Binds
        fun bindContext(application: Application): Context
    }
}