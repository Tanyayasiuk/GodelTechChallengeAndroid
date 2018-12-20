package com.example.tanya.godeltechchallengeandroid.ui.start

import com.example.tanya.godeltechchallengeandroid.domain.DomainContract
import com.example.tanya.godeltechchallengeandroid.domain.entity.Word
import dagger.Module
import dagger.Provides
import io.reactivex.Observable

@Module
class TestDomainModule {

    @Provides
    fun providesDomainContractCounWordsUseCase(testApp: TestApp): DomainContract.CountWordsUseCase {
        return object : DomainContract.CountWordsUseCase {
            override fun execute(url: String): Observable<List<Word>> {
                return testApp.testUseCaseDelegate.countWordsObservable!!
            }
        }
    }
}