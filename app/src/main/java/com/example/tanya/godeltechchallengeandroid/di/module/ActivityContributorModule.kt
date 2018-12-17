package com.example.tanya.godeltechchallengeandroid.di.module

import android.content.res.Resources
import com.example.tanya.godeltechchallengeandroid.R
import com.example.tanya.godeltechchallengeandroid.ui.next.HomeActivity
import com.example.tanya.godeltechchallengeandroid.ui.start.StartActivity
import com.example.tanya.godeltechchallengeandroid.ui.start.StartContract
import com.example.tanya.godeltechchallengeandroid.ui.start.StartPresenter
import com.example.tanya.godeltechchallengeandroid.util.TimeUnit
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityContributorModule {

    @ContributesAndroidInjector(modules = [StartModule::class])
    fun startActivity(): StartActivity

    @ContributesAndroidInjector
    fun homeActivity(): HomeActivity

    @Binds
    fun bindsStartPresenter(startPresenter: StartPresenter): StartContract.Presenter

    @Module
    class StartModule {
        @Provides
        fun provideTimeUnit(resources: Resources): TimeUnit {
            return TimeUnit(resources.getInteger(R.integer.delay).toLong(), java.util.concurrent.TimeUnit.MILLISECONDS)
        }
    }
}