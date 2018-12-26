package com.example.tanya.godeltechchallengeandroid.ui.main

import android.os.Bundle
import android.widget.Toast
import com.example.tanya.godeltechchallengeandroid.R
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity: DaggerAppCompatActivity(), MainContract.View {

    @Inject lateinit var presenter: MainPresenter
    @Inject lateinit var wordAdapter: WordAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_words_counts.adapter = wordAdapter
        presenter.bindView(this)
    }

    override fun getUrlChangeObservable(): Observable<String> {
        return RxTextView.textChanges(edt_url).map { it.toString() }
    }

    override fun getStartButtonClickObservable(): Observable<Any> {
        return RxView.clicks(btn_start)
    }

    override fun setViewState(viewState: MainContract.ViewState) {
        when (viewState) {
            is MainContract.ViewState.Started -> handleStartedViewState()
            is MainContract.ViewState.ResultReceived -> handleResultReceivedViewState(viewState)
            is MainContract.ViewState.Completed -> handleSucceedViewState()
            is MainContract.ViewState.Failed -> handleFailedViewState(viewState)
        }
    }

    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }

    private fun handleStartedViewState(){
        setControlsEnabled(false)
        wordAdapter.setProgressEnabled(true)
    }

    private fun handleResultReceivedViewState(viewState: MainContract.ViewState.ResultReceived){
        wordAdapter.setItems(viewState.words)
    }

    private fun handleSucceedViewState() {
        setControlsEnabled(true)
        wordAdapter.setProgressEnabled(false)
    }

    private fun handleFailedViewState(viewState: MainContract.ViewState.Failed) {
        setControlsEnabled(true)
        wordAdapter.setProgressEnabled(false)
        Toast.makeText(this, viewState.throwable.localizedMessage, Toast.LENGTH_LONG).show()
    }

    private fun setControlsEnabled(isEnabled: Boolean) {
        btn_start.isEnabled = isEnabled
        edt_url.isEnabled = isEnabled
    }
}