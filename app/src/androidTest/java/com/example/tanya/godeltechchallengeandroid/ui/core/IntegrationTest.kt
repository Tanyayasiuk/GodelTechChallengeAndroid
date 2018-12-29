package com.example.tanya.godeltechchallengeandroid.ui.core

import android.app.Activity
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import com.example.tanya.godeltechchallengeandroid.TestApp
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.rules.RuleChain

abstract class IntegrationTest(activityClass: Class<out Activity>) {

    protected val apiMocksController: ApiMocksController = ApiMocksController()

    private val rxJavaIdlingResourceTestRule = RxJavaIdlingResourceTestRule()

    private val intentsTestRule = IntentsTestRule(activityClass)

    @Rule
    @JvmField
    val ruleChain = RuleChain.outerRule(rxJavaIdlingResourceTestRule).around(intentsTestRule)

    @Before
    fun onBefore() {
        getTestApp().apiMocksControllerSubcomponent.inject(apiMocksController)
        apiMocksController.onBefore()
    }

    private fun getTestApp(): TestApp {
        return getActivity().application as TestApp
    }

    private fun getActivity(): Activity {
        return intentsTestRule.activity
    }

    protected fun assertToast(expectedText: String) {
        Espresso.onView(ViewMatchers.withText(expectedText))
            .inRoot(RootMatchers.withDecorView(Matchers.not(Matchers.`is`(getActivity().window.decorView))))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}