package com.example.tanya.godeltechchallengeandroid

import com.example.tanya.godeltechchallengeandroid.ui.core.ApiMocksController
import com.example.tanya.godeltechchallengeandroid.ui.core.ApiMocksControllerSubcomponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class TestApp : App() {

    lateinit var apiMocksControllerSubcomponent: AndroidInjector<ApiMocksController>

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        val testAppComponent = DaggerTestAppComponent.builder().create(this) as TestAppComponent

        apiMocksControllerSubcomponent = testAppComponent.apiMocksControllerSubcomponentBuilder().build()

        return testAppComponent
    }
}