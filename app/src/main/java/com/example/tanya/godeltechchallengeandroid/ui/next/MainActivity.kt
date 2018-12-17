package com.example.tanya.godeltechchallengeandroid.ui.next

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Toast
import com.example.tanya.godeltechchallengeandroid.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity: DaggerAppCompatActivity(), MainContract.View {

    @Inject lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.bindView(this)

        initView()
    }

    private fun initView() {
        btn_start_loading.setOnClickListener {
            presenter.loadFile(getUrlFromEditText())
        }

        helper.movementMethod = ScrollingMovementMethod()
    }

    private fun getUrlFromEditText(): String {
        return if (edt_url.text!!.isEmpty()) edt_url.hint.toString() else edt_url.text.toString()
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showText(text: String) {
        helper.text = text
    }

    override fun showErrorInvalidUrl() {
        edt_url.error = "Please check the url" //TODO: Replace with the String resource
    }

    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }
}