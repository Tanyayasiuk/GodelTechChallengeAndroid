package com.example.tanya.godeltechchallengeandroid.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import android.app.Application
import dagger.Binds
import com.example.tanya.godeltechchallengeandroid.App
import com.example.tanya.godeltechchallengeandroid.api.ApiModule
import com.example.tanya.godeltechchallengeandroid.data.prefs.ApplicationRepositoryImpl
import com.example.tanya.godeltechchallengeandroid.data.prefs.ApplicationRepository
import javax.inject.Singleton


@Module(includes = [ApiModule::class])
abstract class AppModule {

    @Binds
    abstract fun bindApplication(app: App): Application

    @Binds
    abstract fun bindContext(application: Application): Context

    @Binds
    abstract fun bindApplicationRepository(applicationRepositoryImpl: ApplicationRepositoryImpl): ApplicationRepository

}