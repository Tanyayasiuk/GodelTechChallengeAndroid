package com.example.tanya.godeltechchallengeandroid.ui.start

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class EspressoJUnitRunner: AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, TestApp::class.java.name, context)
    }
}