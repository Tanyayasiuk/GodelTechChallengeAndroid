package com.example.tanya.godeltechchallengeandroid.di.module

import com.example.tanya.godeltechchallengeandroid.ui.next.HomeActivity
import com.example.tanya.godeltechchallengeandroid.ui.start.StartActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityContributorModule {

    @ContributesAndroidInjector(modules = [UiModule.StartModule::class])
    fun startActivity (): StartActivity

    @ContributesAndroidInjector
    fun homeActivity (): HomeActivity
}