package com.example.tanya.godeltechchallengeandroid.ui.start

interface StartContract {

    interface View  {
        fun navigateToHomeScreen()
        fun showError(errorMessage: String)
    }

    interface Presenter {
        fun bindView(view: View)
        fun destroy()
    }
}