package com.example.tanya.godeltechchallengeandroid.di.module

import android.content.res.Resources
import com.example.tanya.godeltechchallengeandroid.R
import com.example.tanya.godeltechchallengeandroid.ui.start.StartContract
import com.example.tanya.godeltechchallengeandroid.ui.start.StartPresenter
import com.example.tanya.godeltechchallengeandroid.util.TimeUnit
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class UiModule {

    @Module
    abstract class StartModule {

        @Module
        companion object {
            @Provides
            @JvmStatic
            fun provideTimeUnit(resources: Resources): TimeUnit {
                return TimeUnit(resources.getInteger(R.integer.delay).toLong(), java.util.concurrent.TimeUnit.MILLISECONDS)
            }
        }

        @Binds
        abstract fun provideStartPresenter(startPresenter: StartPresenter): StartContract.Presenter


    }
}
