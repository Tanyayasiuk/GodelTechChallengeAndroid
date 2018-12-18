package com.example.tanya.godeltechchallengeandroid.ui.main

import android.os.Bundle
import android.widget.Toast
import com.example.tanya.godeltechchallengeandroid.R
import com.example.tanya.godeltechchallengeandroid.domain.entity.Word
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity: DaggerAppCompatActivity(), MainContract.View {

    @Inject lateinit var presenter: MainPresenter
    @Inject lateinit var wordAdapter: WordAdapter

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

        rv_words_counts.adapter = wordAdapter
    }

    private fun getUrlFromEditText(): String {
        return if (edt_url.text!!.isEmpty()) edt_url.hint.toString() else edt_url.text.toString()
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showData(set: MutableMap<String, Int>) {
        wordAdapter.setItems(set)
    }

    override fun addData(item: Word) {
        wordAdapter.addItem(item)
    }

    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }
}