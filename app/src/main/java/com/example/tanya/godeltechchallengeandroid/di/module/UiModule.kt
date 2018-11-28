package com.example.tanya.godeltechchallengeandroid.di.module

import com.example.tanya.godeltechchallengeandroid.ui.start.StartContract
import com.example.tanya.godeltechchallengeandroid.ui.start.StartPresenter
import dagger.Module
import dagger.Binds

@Module
class UiModule {

    @Module
    abstract class StartModule {

        @Binds
        abstract fun provideStartPresenter(startPresenter: StartPresenter): StartContract.Presenter
    }
}
