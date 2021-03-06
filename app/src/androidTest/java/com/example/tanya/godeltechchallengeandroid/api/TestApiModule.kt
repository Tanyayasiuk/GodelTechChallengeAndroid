package com.example.tanya.godeltechchallengeandroid.api

import com.example.tanya.godeltechchallengeandroid.api.preferences.PreferencesModule
import com.example.tanya.godeltechchallengeandroid.api.rest.TestRestModule
import com.example.tanya.godeltechchallengeandroid.api.text.TextModule
import dagger.Module

@Module(
    includes = [
        PreferencesModule::class,
        TestRestModule::class,
        TextModule::class
    ]
)
interface TestApiModule
