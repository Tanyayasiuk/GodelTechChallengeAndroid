package com.example.tanya.godeltechchallengeandroid.domain

import com.example.tanya.godeltechchallengeandroid.domain.entity.Word
import io.reactivex.Observable

interface DomainContract {

    interface CountWordsUseCase {
        fun execute(uri: String): Observable<List<Word>>
    }

    interface StartupUseCase {
        fun execute(input: Unit? = null): Observable<Unit>
    }
}