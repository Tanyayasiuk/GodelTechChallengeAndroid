package com.example.tanya.godeltechchallengeandroid.domain.interactor

import com.example.tanya.godeltechchallengeandroid.data.prefs.ApplicationRepository
import com.example.tanya.godeltechchallengeandroid.domain.rx.NoValue
import io.reactivex.Observable
import javax.inject.Inject

open class SetStartStatus
@Inject constructor(private val applicationRepository: ApplicationRepository): BaseUseCase<Boolean, Unit>() {
    override fun createObservable(input: Boolean?): Observable<Unit> {
        applicationRepository.isFirstStart = input!!
        return Observable.just(Unit)
    }
}