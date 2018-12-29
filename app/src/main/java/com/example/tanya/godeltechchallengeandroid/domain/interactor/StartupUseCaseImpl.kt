package com.example.tanya.godeltechchallengeandroid.domain.interactor

import com.example.tanya.godeltechchallengeandroid.data.DataContract
import com.example.tanya.godeltechchallengeandroid.domain.DomainContract
import io.reactivex.Observable
import javax.inject.Inject

open class StartupUseCaseImpl
@Inject constructor(private val applicationRepository: DataContract.ApplicationRepository) :
    DomainContract.StartupUseCase {

    override fun execute(input: Unit?): Observable<Unit> {
        return Observable.fromCallable {
            if (applicationRepository.isFirstStart.not()) {
                applicationRepository.isFirstStart = true
            }
        }
    }
}