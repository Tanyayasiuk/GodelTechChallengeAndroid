package com.example.tanya.godeltechchallengeandroid.ui.start

interface StartContract {

    interface View  {
        fun navigateToHomeScreen()
    }

    interface Presenter {
        fun bindView(view: View)
        fun loadData()
    }
}