package com.example.tanya.godeltechchallengeandroid.ui.start

import com.example.tanya.godeltechchallengeandroid.App
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class TestApp : App() {

    @Inject
    lateinit var testUseCaseDelegate: TestUseCaseDelegate

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerTestAppComponent.builder().create(this)
    }
}