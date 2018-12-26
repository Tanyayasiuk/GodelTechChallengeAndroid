package com.example.tanya.godeltechchallengeandroid.domain.interactor

import com.example.tanya.godeltechchallengeandroid.data.DataContract
import com.example.tanya.godeltechchallengeandroid.domain.DomainContract
import com.example.tanya.godeltechchallengeandroid.domain.entity.Word
import io.reactivex.Observable
import javax.inject.Inject

class CountWordsUseCaseImpl @Inject constructor(
    private val fileRepository: DataContract.FileRepository,
    private val wordCountRepository: DataContract.WordCountRepository
) : DomainContract.CountWordsUseCase {

    override fun execute(uri: String): Observable<List<Word>> {
        return fileRepository
            .getFileInputStream(uri)
            .flatMapObservable { inputStream ->
                wordCountRepository
                    .getWordCountsObservable(inputStream)
                    .map { wordCounts ->
                        wordCounts.map { wordCount ->
                            Word(wordCount.first, wordCount.second)
                        }
                    }
            }
    }
}