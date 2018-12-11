package com.example.tanya.godeltechchallengeandroid.ui.start

import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.tanya.godeltechchallengeandroid.ui.next.HomeActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class StartActivityTest {

    private var idlingResource: StartIdlingResource? = null

    @Rule
    @JvmField
    val activityRule = IntentsTestRule(StartActivity::class.java)

    @Before
    fun onBefore() {
        idlingResource = StartIdlingResource(5000)
        IdlingRegistry.getInstance().register(idlingResource)
    }

    @After
    fun onAfter() {
        IdlingRegistry.getInstance().unregister(idlingResource)
    }

    @Test
    fun navigateToHomeScreen() {
        intended(hasComponent(HomeActivity::class.java.getName()))
    }
}
