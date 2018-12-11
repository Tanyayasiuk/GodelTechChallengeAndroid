package com.example.tanya.godeltechchallengeandroid.ui.start

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric.buildActivity
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class StartActivityIntegrationTest {

    @Test
    fun onStartActivityCreated_shouldInjectDaggerDependencies() {
        buildActivity(StartActivity::class.java).setup()
    }
}
