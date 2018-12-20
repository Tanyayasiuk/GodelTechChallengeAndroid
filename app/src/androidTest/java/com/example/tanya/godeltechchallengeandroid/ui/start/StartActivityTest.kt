package com.example.tanya.godeltechchallengeandroid.ui.start

import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import com.example.tanya.godeltechchallengeandroid.ui.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain

class StartActivityTest {

    @Rule
    @JvmField
    val ruleChain =
        RuleChain
            .outerRule(RxJavaIdlingResourceTestRule())
            .around(IntentsTestRule(StartActivity::class.java))

    @Test
    fun navigateToHomeScreen() {
        intended(hasComponent(MainActivity::class.java.name))
    }
}
