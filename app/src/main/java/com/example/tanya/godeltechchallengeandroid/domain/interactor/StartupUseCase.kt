package com.example.tanya.godeltechchallengeandroid.domain.interactor

import com.example.tanya.godeltechchallengeandroid.data.prefs.ApplicationRepository
import io.reactivex.Observable
import javax.inject.Inject

open class StartupUseCase
@Inject constructor(private val applicationRepository: ApplicationRepository): BaseUseCase<Unit, Unit>() {
    override fun createObservable(input: Unit?): Observable<Unit> {
        return Observable.fromCallable {
            if(applicationRepository.isFirstStart.not()) {
                applicationRepository.isFirstStart = true
            }
        }
    }
}