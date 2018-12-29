package com.example.tanya.godeltechchallengeandroid.ui.core

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface ApiMocksControllerSubcomponent : AndroidInjector<ApiMocksController> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<ApiMocksController>()
}