package com.example.tanya.godeltechchallengeandroid.domain.interactor

import com.example.tanya.godeltechchallengeandroid.domain.DomainContract
import com.example.tanya.godeltechchallengeandroid.domain.entity.Word
import io.reactivex.Observable
import javax.inject.Inject

class CountWordsUseCaseImpl @Inject constructor() : DomainContract.CountWordsUseCase {
    override fun execute(url: String): Observable<List<Word>> = TODO()
}