package com.example.tanya.godeltechchallengeandroid.di.module

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.example.tanya.godeltechchallengeandroid.App
import com.example.tanya.godeltechchallengeandroid.api.ApiModule
import com.example.tanya.godeltechchallengeandroid.data.prefs.ApplicationRepository
import com.example.tanya.godeltechchallengeandroid.data.prefs.ApplicationRepositoryImpl
import com.example.tanya.godeltechchallengeandroid.domain.DomainModule
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module(
    includes = [
        ApiModule::class,
        DomainModule::class
    ]
)
abstract class AppModule {

    @Binds
    abstract fun bindApplication(app: App): Application

    @Binds
    abstract fun bindContext(application: Application): Context

    @Binds
    abstract fun bindApplicationRepository(applicationRepositoryImpl: ApplicationRepositoryImpl): ApplicationRepository

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideResources(context: Context): Resources =
            context.resources
    }

}