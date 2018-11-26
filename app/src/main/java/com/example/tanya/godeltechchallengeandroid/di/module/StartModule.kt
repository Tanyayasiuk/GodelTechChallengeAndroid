package com.example.tanya.godeltechchallengeandroid.di.module

import com.example.tanya.godeltechchallengeandroid.ui.start.StartContract
import com.example.tanya.godeltechchallengeandroid.ui.start.StartPresenter
import dagger.Module
import dagger.Provides


@Module
class StartModule {

    @Provides
    fun providePresenter(): StartContract.Presenter {
        return StartPresenter()
    }
}