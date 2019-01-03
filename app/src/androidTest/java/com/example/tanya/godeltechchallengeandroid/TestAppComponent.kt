package com.example.tanya.godeltechchallengeandroid

import com.example.tanya.godeltechchallengeandroid.api.TestApiModule
import com.example.tanya.godeltechchallengeandroid.dagger.TestScope
import com.example.tanya.godeltechchallengeandroid.di.module.AppModule
import com.example.tanya.godeltechchallengeandroid.domain.DomainModule
import com.example.tanya.godeltechchallengeandroid.ui.UiModule
import com.example.tanya.godeltechchallengeandroid.ui.core.IntegrationTest
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@TestScope
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        TestBindings::class,
        AppModule::class,
        TestApiModule::class,
        DomainModule::class,
        UiModule::class
    ]
)
interface TestAppComponent : AndroidInjector<TestApp> {

    fun inject(integrationTest: IntegrationTest)

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<TestApp>()
}

@Module
interface TestBindings {

    @Binds
    fun bindsToApp(testApp: TestApp): App
}

