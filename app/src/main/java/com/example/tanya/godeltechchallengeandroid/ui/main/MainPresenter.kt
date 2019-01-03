package com.example.tanya.godeltechchallengeandroid.ui.main

import com.example.tanya.godeltechchallengeandroid.domain.DomainContract
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.withLatestFrom
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainPresenter
@Inject constructor(private val countWordsUseCase: DomainContract.CountWordsUseCase) : MainContract.Presenter {

    private var view: MainContract.View? = null
    private val disposables = CompositeDisposable()

    override fun bindView(view: MainContract.View) {
        this.view = view

        view.getStartButtonClickObservable()
            .withLatestFrom(view.getUrlChangeObservable()) { _, url -> url }
            .flatMap { url ->
                countWordsUseCase
                    .execute(url)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { view.setViewState(MainContract.ViewState.Started) }
                    .doOnNext { view.setViewState(MainContract.ViewState.ResultReceived(it)) }
                    .doOnError { view.setViewState(MainContract.ViewState.Failed(it)) }
                    .doOnComplete { view.setViewState(MainContract.ViewState.Completed) }
                    .onErrorResumeNext { th: Throwable ->
                        Observable.empty()
                    }
            }
            .subscribe()
            .addTo(disposables)
    }

    override fun destroy() {
        disposables.clear()
        this.view = null
    }
}
