package com.example.tanya.godeltechchallengeandroid.di.module

import android.content.res.Resources
import com.example.tanya.godeltechchallengeandroid.R
import com.example.tanya.godeltechchallengeandroid.ui.main.MainActivity
import com.example.tanya.godeltechchallengeandroid.ui.main.MainContract
import com.example.tanya.godeltechchallengeandroid.ui.main.MainPresenter
import com.example.tanya.godeltechchallengeandroid.ui.start.StartActivity
import com.example.tanya.godeltechchallengeandroid.ui.start.StartContract
import com.example.tanya.godeltechchallengeandroid.ui.start.StartPresenter
import com.example.tanya.godeltechchallengeandroid.util.TimeUnit
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Named

const val LOADING_TIME_UNIT = "LOADING_TIME_UNIT"

@Module
interface ActivityContributorModule {

    @ContributesAndroidInjector(modules = [StartModule::class])
    fun startActivity(): StartActivity

    @ContributesAndroidInjector
    fun mainActivity(): MainActivity

    @Binds
    fun bindsStartPresenter(startPresenter: StartPresenter): StartContract.Presenter

    @Binds
    fun bindsMainPresenter(mainPresenter: MainPresenter): MainContract.Presenter

    @Module
    class StartModule {
        @Named(LOADING_TIME_UNIT)
        @Provides
        fun provideTimeUnit(resources: Resources): TimeUnit {
            return TimeUnit(resources.getInteger(R.integer.delay).toLong(), java.util.concurrent.TimeUnit.MILLISECONDS)
        }
    }
}