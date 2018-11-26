package com.example.tanya.godeltechchallengeandroid.di.component

import com.example.tanya.godeltechchallengeandroid.App
import com.example.tanya.godeltechchallengeandroid.di.module.ActivityBuilder
import com.example.tanya.godeltechchallengeandroid.di.module.AppModule
import com.example.tanya.godeltechchallengeandroid.di.module.StartModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules= [AppModule::class, AndroidInjectionModule::class, ActivityBuilder::class, StartModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }

    fun inject (app: App)
}