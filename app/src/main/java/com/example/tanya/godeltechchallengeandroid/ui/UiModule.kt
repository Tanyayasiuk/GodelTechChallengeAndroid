package com.example.tanya.godeltechchallengeandroid.ui

import com.example.tanya.godeltechchallengeandroid.di.module.ActivityContributorModule
import dagger.Module

@Module(
    includes = [
        ActivityContributorModule::class
    ]
)
class UiModule
