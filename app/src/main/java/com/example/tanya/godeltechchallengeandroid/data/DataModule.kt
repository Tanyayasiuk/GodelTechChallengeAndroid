package com.example.tanya.godeltechchallengeandroid.data

import android.content.res.Resources
import com.example.tanya.godeltechchallengeandroid.R
import com.example.tanya.godeltechchallengeandroid.data.prefs.ApplicationRepositoryImpl
import com.example.tanya.godeltechchallengeandroid.util.TimeUnit
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named

const val BUFFER_TIME_SPAN = "BUFFER_TIME_SPAN"

@Module(includes = [DataModule.WordCountModule::class])
interface DataModule {

    @Binds
    fun bindsToApplicationRepository(applicationRepositoryImpl: ApplicationRepositoryImpl): DataContract.ApplicationRepository

    @Binds
    fun bindsToFileRepository(impl: FileRepositoryImpl): DataContract.FileRepository

    @Binds
    fun bindsToWordCountRepository(impl: WordCountRepositoryImpl): DataContract.WordCountRepository

    @Module
    class WordCountModule {
        @Named(BUFFER_TIME_SPAN)
        @Provides
        fun providesTimeUnit(resources: Resources): TimeUnit {
            return TimeUnit(resources.getInteger(R.integer.timespan).toLong(), java.util.concurrent.TimeUnit.SECONDS)
        }
    }
}