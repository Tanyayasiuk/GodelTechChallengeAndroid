package com.example.tanya.godeltechchallengeandroid.data

import com.example.tanya.godeltechchallengeandroid.api.ApiContract
import com.example.tanya.godeltechchallengeandroid.util.Timespan
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.io.InputStream
import javax.inject.Inject

class WordCountRepositoryImpl @Inject constructor(private val textApi: ApiContract.TextApi,
                                                  private val timespan: Timespan) : DataContract.WordCountRepository {

    override fun getWordCountsObservable(inputStream: InputStream): Observable<List<Pair<String, Int>>> {
        return textApi
            .getWordsObservable(inputStream)
            .buffer(timespan.timespan, timespan.timeUnit)
            .lift { observer ->
                object : Observer<List<String>> {

                    private val word2count = mutableMapOf<String, Int>()

                    override fun onSubscribe(d: Disposable) {
                        observer.onSubscribe(d)
                    }

                    override fun onNext(words: List<String>) {
                        words.forEach { word ->
                            word2count[word] = word2count[word]?.inc() ?: 1
                        }

                        observer.onNext(
                            word2count
                                .toList()
                                .sortedByDescending { it.second }
                        )
                    }

                    override fun onComplete() {
                        observer.onComplete()
                    }

                    override fun onError(e: Throwable) {
                        observer.onError(e)
                    }
                }
            }
    }
}