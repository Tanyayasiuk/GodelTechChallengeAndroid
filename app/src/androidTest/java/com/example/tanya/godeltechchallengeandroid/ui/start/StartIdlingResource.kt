package com.example.tanya.godeltechchallengeandroid.ui.start

import androidx.test.espresso.IdlingResource

class StartIdlingResource(private val waitingTime: Long): IdlingResource {
    private val startTime: Long = System.currentTimeMillis()
    private var resourceCallback: IdlingResource.ResourceCallback? = null

    override fun getName(): String {
        return StartIdlingResource::class.java.name
    }

    override fun isIdleNow(): Boolean {
        val elapsed = System.currentTimeMillis() - startTime
        val idle = elapsed >= waitingTime
        if (idle) {
            resourceCallback!!.onTransitionToIdle()
        }
        return idle
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.resourceCallback = callback
    }
}