package com.example.tanya.godeltechchallengeandroid.api.text

import com.example.tanya.godeltechchallengeandroid.api.ApiContract
import dagger.Binds
import dagger.Module

@Module
interface TextModule {

    @Binds
    fun bindsTextApi(textApiImpl: TextApiImpl): ApiContract.TextApi
}