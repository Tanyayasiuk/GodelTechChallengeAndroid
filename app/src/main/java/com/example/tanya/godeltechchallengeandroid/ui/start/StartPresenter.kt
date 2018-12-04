package com.example.tanya.godeltechchallengeandroid.ui.start

import com.example.tanya.godeltechchallengeandroid.domain.interactor.SetStartStatus
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class StartPresenter
@Inject
constructor(private val setStartStatus: SetStartStatus) : StartContract.Presenter {

    private var view: StartContract.View? = null
    private val disposables = CompositeDisposable()

    override fun bindView(view: StartContract.View) {
        this.view = view
    }

    /*Imitating data loading*/
    override fun loadData() {

        val subscription = setStartStatus
                                .execute(false)
                                .delay(5000, TimeUnit.MILLISECONDS)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({
                                    view?.navigateToHomeScreen()
                                }, {
                                    view?.showError(it.localizedMessage)
                                })
        disposables.add(subscription)
    }

    override fun destroy() {
        this.disposables.clear()
        this.view = null
    }
}
