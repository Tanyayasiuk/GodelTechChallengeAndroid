package com.example.tanya.godeltechchallengeandroid.ui.start

import com.example.tanya.godeltechchallengeandroid.domain.entity.Word
import io.reactivex.Observable
import javax.inject.Inject

class TestUseCaseDelegate @Inject constructor() {
    var countWordsObservable: Observable<List<Word>>? = null
}