package com.example.tanya.godeltechchallengeandroid.domain.interactor

import io.reactivex.Observable

//TODO: Review for possible updates depending on further particular useCases
abstract class BaseUseCase<Input, Output> {

    fun execute(input: Input? = null): Observable<Output> {
        return createObservable(input)
    }

    protected abstract fun createObservable(input: Input?): Observable<Output>

}