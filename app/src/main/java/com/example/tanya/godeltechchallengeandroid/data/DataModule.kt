package com.example.tanya.godeltechchallengeandroid.data

import android.content.res.Resources
import com.example.tanya.godeltechchallengeandroid.R
import com.example.tanya.godeltechchallengeandroid.data.prefs.ApplicationRepositoryImpl
import com.example.tanya.godeltechchallengeandroid.util.TimeUnit
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
abstract class DataModule {

    @Binds
    abstract fun bindsToApplicationRepository(applicationRepositoryImpl: ApplicationRepositoryImpl): DataContract.ApplicationRepository

    @Binds
    abstract fun bindsToFileRepository(impl: FileRepositoryImpl): DataContract.FileRepository

    @Binds
    abstract fun bindsToWordCountRepository(impl: WordCountRepositoryImpl): DataContract.WordCountRepository

    @Module
    companion object {
        @Named("time_span")
        @Provides
        @JvmStatic
        fun providesTimeUnit(resources: Resources): TimeUnit {
            return TimeUnit(resources.getInteger(R.integer.timespan).toLong(), java.util.concurrent.TimeUnit.SECONDS)
        }
    }
}