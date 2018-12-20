package com.example.tanya.godeltechchallengeandroid.ui.start

import com.example.tanya.godeltechchallengeandroid.App
import com.example.tanya.godeltechchallengeandroid.api.ApiModule
import com.example.tanya.godeltechchallengeandroid.di.module.AppModule
import com.example.tanya.godeltechchallengeandroid.ui.UiModule
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        TestBindings::class,
        AppModule::class,
        ApiModule::class,
        TestDomainModule::class,
        UiModule::class
    ]
)
interface TestAppComponent : AndroidInjector<TestApp> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<TestApp>()
}

@Module
interface TestBindings {
    @Binds
    fun bindsToApp(testApp: TestApp): App
}


