package com.example.tanya.godeltechchallengeandroid.di.component

import com.example.tanya.godeltechchallengeandroid.di.module.ActivityContributorModule
import com.example.tanya.godeltechchallengeandroid.di.module.AppModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import com.example.tanya.godeltechchallengeandroid.App
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityContributorModule::class]
)
internal interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}