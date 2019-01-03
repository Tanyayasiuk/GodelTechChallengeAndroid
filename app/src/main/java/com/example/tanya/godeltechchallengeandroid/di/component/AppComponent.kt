package com.example.tanya.godeltechchallengeandroid.di.component

import com.example.tanya.godeltechchallengeandroid.App
import com.example.tanya.godeltechchallengeandroid.api.ApiModule
import com.example.tanya.godeltechchallengeandroid.di.module.AppModule
import com.example.tanya.godeltechchallengeandroid.domain.DomainModule
import com.example.tanya.godeltechchallengeandroid.ui.UiModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ApiModule::class,
        DomainModule::class,
        UiModule::class
    ]
)
internal interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}