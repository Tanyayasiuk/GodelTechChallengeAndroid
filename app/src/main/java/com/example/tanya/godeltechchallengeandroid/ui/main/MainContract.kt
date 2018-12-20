package com.example.tanya.godeltechchallengeandroid.ui.main

import com.example.tanya.godeltechchallengeandroid.domain.entity.Word
import io.reactivex.Observable

interface MainContract {

    interface View {
        fun getUrlChangeObservable(): Observable<String>
        fun getStartButtonClickObservable(): Observable<Any>
        fun setViewState(viewState: ViewState)
    }

    interface Presenter{
        fun bindView(view: View)
        fun destroy()
    }

    sealed class ViewState {
        object Started: ViewState()
        data class ResultReceived(val words: List<Word>): ViewState()
        data class Failed(val throwable: Throwable): ViewState()
        object Completed: ViewState()
    }
}