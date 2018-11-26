package com.example.tanya.godeltechchallengeandroid.ui.start

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.example.tanya.godeltechchallengeandroid.R
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_start.*
import javax.inject.Inject

class StartActivity : AppCompatActivity(), StartContract.View {

    @Inject lateinit var presenter: StartContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        presenter.bindView(this)

        presenter.loadData()
    }

    override fun navigateToHomeScreen() {
        //TODO: implement navigating to Home screen
        Toast.makeText(this, resources.getText(R.string.loading_done), Toast.LENGTH_SHORT).show()
        progress_bar.visibility = View.INVISIBLE
        lbl_loading.visibility = View.INVISIBLE
    }
}
