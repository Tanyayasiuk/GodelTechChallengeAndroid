package com.example.tanya.godeltechchallengeandroid.ui.main

import com.example.tanya.godeltechchallengeandroid.domain.interactor.DownloadFileUseCase
import com.example.tanya.godeltechchallengeandroid.domain.interactor.ParseTextUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class MainPresenter
    @Inject constructor(private val downloadFileUseCase: DownloadFileUseCase,
                        private val parseTextUseCase: ParseTextUseCase)
    : MainContract.Presenter {

    private var view: MainContract.View? = null
    private val disposables = CompositeDisposable()

    override fun bindView(view: MainContract.View) {
        this.view = view
    }

    override fun loadFile(url: String) {
        downloadFileUseCase.execute(url)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                parseTextUseCase.execute(it)
                    .subscribe({ word ->
                        view?.addData(word) //TODO: change with recyclerView fulfilling
                    }, { throwable ->
                        view?.showError(throwable.localizedMessage)
                    })
            }, {
                view?.showError(it.localizedMessage)
            })
            .addTo(disposables)
    }

    override fun destroy() {
        disposables.clear()
        this.view = null
    }
}
