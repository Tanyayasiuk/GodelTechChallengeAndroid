package com.example.tanya.godeltechchallengeandroid.domain.interactor

import com.example.tanya.godeltechchallengeandroid.data.prefs.PreferenceStorage
import io.reactivex.Observable
import javax.inject.Inject

class SetStartStatus
@Inject constructor(private val prefs: PreferenceStorage): BaseUseCase<Boolean, Unit>() {
    override fun createObservable(input: Boolean?): Observable<Unit> {
        prefs.isFirstStart = input!!
        return Observable.just(Unit)
    }
}