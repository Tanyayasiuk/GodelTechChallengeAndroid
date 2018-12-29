package com.example.tanya.godeltechchallengeandroid.ui.start

import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import com.example.tanya.godeltechchallengeandroid.ui.core.IntegrationTest
import com.example.tanya.godeltechchallengeandroid.ui.main.MainActivity
import org.junit.Test

class StartActivityTest : IntegrationTest(StartActivity::class.java) {

    @Test
    fun navigateToHomeScreen() {
        intended(hasComponent(MainActivity::class.java.name))
    }
}
