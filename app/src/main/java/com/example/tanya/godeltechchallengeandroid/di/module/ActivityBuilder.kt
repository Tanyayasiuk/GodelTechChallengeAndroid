package com.example.tanya.godeltechchallengeandroid.di.module

import com.example.tanya.godeltechchallengeandroid.ui.start.StartActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindStartActivity (): StartActivity
}