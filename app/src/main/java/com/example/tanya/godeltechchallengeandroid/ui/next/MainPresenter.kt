package com.example.tanya.godeltechchallengeandroid.ui.next

import com.example.tanya.godeltechchallengeandroid.domain.interactor.DownloadFileUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class MainPresenter
    @Inject constructor(private val downloadFileUseCase: DownloadFileUseCase)
    : MainContract.Presenter {

    private var view: MainContract.View? = null
    private val disposables = CompositeDisposable()

    override fun bindView(view: MainContract.View) {
        this.view = view
    }

    override fun loadFile(url: String) {
        if(isUrlValid(url)) {
            downloadFileUseCase.execute(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.showText(it)
                }, {
                    view?.showError(it.localizedMessage)
                })
                .addTo(disposables)
        } else {
            view?.showErrorInvalidUrl()
        }
    }

    private fun isUrlValid(url:String): Boolean {
        //TODO: Implement url validation logic (or not))
        return true
    }

    override fun destroy() {
        disposables.clear()
        this.view = null
    }
}
