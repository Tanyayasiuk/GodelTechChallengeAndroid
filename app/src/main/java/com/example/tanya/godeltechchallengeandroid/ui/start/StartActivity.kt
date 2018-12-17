package com.example.tanya.godeltechchallengeandroid.ui.start

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.tanya.godeltechchallengeandroid.R
import com.example.tanya.godeltechchallengeandroid.ui.next.HomeActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_start.*
import javax.inject.Inject

class StartActivity : DaggerAppCompatActivity(), StartContract.View {

    @Inject lateinit var presenter: StartContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        presenter.bindView(this)
    }

    override fun navigateToHomeScreen() {
        Toast.makeText(this, resources.getText(R.string.loading_done), Toast.LENGTH_SHORT).show()
        hideProgress()

        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        hideProgress()
    }

    private fun hideProgress() {
        progress_bar.visibility = View.INVISIBLE
        lbl_loading.visibility = View.INVISIBLE
    }

    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }
}
