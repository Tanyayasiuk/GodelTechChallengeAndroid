package com.example.tanya.godeltechchallengeandroid.ui.main

import com.example.tanya.godeltechchallengeandroid.domain.entity.Word

interface MainContract {

    interface View {
        fun showData(set: MutableMap<String, Int>) //TODO: to be done
        fun addData(item: Word)
        fun showError(message: String)
    }

    interface Presenter{
        //TODO: move repeated methods to some base structure
        fun bindView(view: View)
        fun loadFile(url: String)
        fun destroy()
    }
}