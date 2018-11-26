package com.example.tanya.godeltechchallengeandroid.ui.start

import android.os.Handler
import com.example.tanya.godeltechchallengeandroid.util.Prefs
import javax.inject.Inject

class StartPresenter
    @Inject
    constructor(private val prefs: Prefs): StartContract.Presenter {

    private lateinit var view: StartContract.View

    override fun bindView(view: StartContract.View) {
        this.view = view
    }

    /*Imitating data loading*/
    override fun loadData() {
        Handler().postDelayed(
            {
                prefs.isFirstStart = false
                view.navigateToHomeScreen()
            },
            5000
        )
    }
}
