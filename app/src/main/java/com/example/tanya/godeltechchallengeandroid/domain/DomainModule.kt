package com.example.tanya.godeltechchallengeandroid.domain

import com.example.tanya.godeltechchallengeandroid.domain.interactor.CountWordsUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    fun bindsToDomainContractCountWordsUseCase(impl: CountWordsUseCaseImpl): DomainContract.CountWordsUseCase
}