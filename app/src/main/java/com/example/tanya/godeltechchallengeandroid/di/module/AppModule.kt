package com.example.tanya.godeltechchallengeandroid.di.module

import android.content.Context
import com.example.tanya.godeltechchallengeandroid.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule()
{
    @Provides
    @Singleton
    fun provideApplication(app : App): Context = app
}