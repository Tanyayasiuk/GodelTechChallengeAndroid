package com.example.tanya.godeltechchallengeandroid.domain.interactor

import com.example.tanya.godeltechchallengeandroid.data.prefs.ApplicationRepository
import io.reactivex.Observable
import javax.inject.Inject

class SetStartStatus
@Inject constructor(private val prefs: ApplicationRepository): BaseUseCase<Boolean, Unit>() {
    override fun createObservable(input: Boolean?): Observable<Unit> {
        prefs.isFirstStart = input!!
        return Observable.just(Unit)
    }
}