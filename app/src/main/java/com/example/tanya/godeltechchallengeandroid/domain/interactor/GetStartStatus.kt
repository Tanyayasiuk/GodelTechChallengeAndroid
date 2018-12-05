package com.example.tanya.godeltechchallengeandroid.domain.interactor

import com.example.tanya.godeltechchallengeandroid.data.prefs.ApplicationRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetStartStatus
    @Inject constructor(private val prefs: ApplicationRepository): BaseUseCase<Unit, Boolean>() {

    override fun createObservable(input: Unit?): Observable<Boolean> {
        return Observable.just(prefs.isFirstStart)
    }
}