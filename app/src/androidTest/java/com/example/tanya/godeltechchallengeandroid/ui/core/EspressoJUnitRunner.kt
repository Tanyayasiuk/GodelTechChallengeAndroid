package com.example.tanya.godeltechchallengeandroid.ui.core

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.example.tanya.godeltechchallengeandroid.TestApp

class EspressoJUnitRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, TestApp::class.java.name, context)
    }
}