package com.example.tanya.godeltechchallengeandroid.ui.start

import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.tanya.godeltechchallengeandroid.ui.next.HomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class StartActivityTest {

    @Rule
    @JvmField
    val ruleChain =
        RuleChain
            .outerRule(RxJavaIdlingResourceTestRule())
            .around(IntentsTestRule(StartActivity::class.java))

    @Test
    fun navigateToHomeScreen() {
        intended(hasComponent(HomeActivity::class.java.name))
    }
}
