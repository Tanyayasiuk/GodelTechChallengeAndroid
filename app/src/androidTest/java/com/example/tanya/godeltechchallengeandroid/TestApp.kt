package com.example.tanya.godeltechchallengeandroid

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class TestApp : App() {

    lateinit var testAppComponent: TestAppComponent

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerTestAppComponent
            .builder()
            .create(this)
            .apply { testAppComponent = this as TestAppComponent }
    }
}