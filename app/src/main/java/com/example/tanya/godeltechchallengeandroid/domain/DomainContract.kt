package com.example.tanya.godeltechchallengeandroid.domain

import com.example.tanya.godeltechchallengeandroid.domain.entity.Word
import io.reactivex.Observable

interface DomainContract {

    interface CountWordsUseCase {
        fun execute(url: String): Observable<List<Word>>
    }
}