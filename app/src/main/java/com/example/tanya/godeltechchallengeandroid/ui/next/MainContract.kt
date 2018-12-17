package com.example.tanya.godeltechchallengeandroid.ui.next

interface MainContract {

    interface View {
        fun showText(text: String) //TODO: to be done
        fun showError(message: String)
        fun showErrorInvalidUrl()
    }

    interface Presenter{
        //TODO: move repeated methods to some base structure
        fun bindView(view: View)
        fun loadFile(url: String)
        fun destroy()
    }
}