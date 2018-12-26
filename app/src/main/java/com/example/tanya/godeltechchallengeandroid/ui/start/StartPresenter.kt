package com.example.tanya.godeltechchallengeandroid.ui.start

import com.example.tanya.godeltechchallengeandroid.domain.interactor.StartupUseCaseImpl
import com.example.tanya.godeltechchallengeandroid.util.TimeUnit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class StartPresenter
@Inject constructor(private val startupUseCaseImpl: StartupUseCaseImpl,
                    private val timeUnit: TimeUnit)
        : StartContract.Presenter {

    private var view: StartContract.View? = null
    private val disposables = CompositeDisposable()

    override fun bindView(view: StartContract.View) {
        this.view = view

        loadData()
    }

    /*Imitating data loading*/
    private fun loadData() {
        startupUseCaseImpl
            .execute()
            .delay(timeUnit.delay, timeUnit.timeUnit)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view?.navigateToHomeScreen()
            }, {
                view?.showError(it.localizedMessage)
            })
            .addTo(disposables)
    }

    override fun destroy() {
        this.disposables.clear()
        this.view = null
    }
}
