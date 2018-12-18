package com.example.tanya.godeltechchallengeandroid.domain.interactor

import com.example.tanya.godeltechchallengeandroid.domain.entity.Word
import io.reactivex.Observable
import java.util.regex.Pattern
import javax.inject.Inject

//TODO: given a line of text (?) split into words, remove punctuation marks,
class ParseTextUseCase @Inject constructor(): BaseUseCase<String, Word>() {

    val pattern = Pattern.compile("(\\w+'\\w+)|(\\w+-\\w+)|(\\w+)")

    override fun createObservable(input: String?): Observable<Word> {
        getSeparateWordsFromLine(input!!)
        return Observable.just(Word(input, 1))
    }

    private fun getSeparateWordsFromLine(line: String) {

            val matcher = pattern.matcher(line)

            while (matcher.find()) {
                //prints separate words
                System.out.println(matcher.group());
            }
    }


}
