package com.example.tanya.godeltechchallengeandroid.domain.interactor

import io.reactivex.Observable

//TODO: Review for possible updates depending on further particular usecases
abstract class BaseUseCase<Input, Output> {

    fun execute(input: Input): Observable<Output> {
        return createObservable(input)
    }

    fun execute(): Observable<Output> {
        return createObservable(null)
    }

    protected abstract fun createObservable(input: Input?): Observable<Output>

}