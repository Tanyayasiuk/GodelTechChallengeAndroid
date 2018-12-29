package com.example.tanya.godeltechchallengeandroid.api.rest

import com.example.tanya.godeltechchallengeandroid.dagger.TestScope
import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides

@Module
class TestRestModule {

    @Provides
    @TestScope
    fun providesRestService() : RestService {
        return mock()
    }

}