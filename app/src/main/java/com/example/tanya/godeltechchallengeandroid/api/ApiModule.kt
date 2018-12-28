package com.example.tanya.godeltechchallengeandroid.api

import com.example.tanya.godeltechchallengeandroid.api.preferences.PreferencesApiImpl
import com.example.tanya.godeltechchallengeandroid.api.preferences.PreferencesModule
import com.example.tanya.godeltechchallengeandroid.api.rest.RestModule
import com.example.tanya.godeltechchallengeandroid.api.textapi.TextApiImpl
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        PreferencesModule::class,
        RestModule::class
    ]
)
interface ApiModule {

    @Binds
    fun bindsPreferencesApi(preferencesApiImpl: PreferencesApiImpl): ApiContract.PreferenceApi

    @Binds
    fun bindsTextApi(textApiImpl: TextApiImpl): ApiContract.TextApi
}
