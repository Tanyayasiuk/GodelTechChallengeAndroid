package com.example.tanya.godeltechchallengeandroid.ui.start

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.tanya.godeltechchallengeandroid.R
import com.example.tanya.godeltechchallengeandroid.domain.entity.Word
import com.example.tanya.godeltechchallengeandroid.ui.main.MainActivity
import io.reactivex.Observable
import kotlinx.android.synthetic.main.item_word_count.view.*
import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain


class MainActivityTest {

    private val rxJavaIdlingResourceTestRule = RxJavaIdlingResourceTestRule()

    private val intentsTestRule = IntentsTestRule(MainActivity::class.java)

    @Rule
    @JvmField
    val ruleChain = RuleChain.outerRule(rxJavaIdlingResourceTestRule).around(intentsTestRule)

    @Test
    fun givenUseCaseDoesNothing_onTypeUrlAndStartButtonClick_shouldDisableControls() {
        givenUseCase(Observable.create { })

        onView(withId(R.id.edt_url)).perform(typeText("text"))
        onView(withId(R.id.btn_start)).perform(click())

        onView(withId(R.id.edt_url)).check(matches(not(isEnabled())))
        onView(withId(R.id.btn_start)).check(matches(not(isEnabled())))
    }

    @Test
    fun givenUseCaseReturnsWords_should() {
        val word1 = Word("word1", 1, true)
        val word2 = Word("word2", 2, false)

        givenUseCase(Observable.just(listOf(word1, word2)))

        onView(withId(R.id.edt_url)).perform(typeText("text"))
        onView(withId(R.id.btn_start)).perform(click())

        onView(withId(R.id.rv_words_counts)).check(matches(allOf(withWord(0, word1), withWord(1, word2))))
    }

    @Test
    fun givenUseCaseReturnsError_shouldEnableControlsAndShowToast() {
        givenUseCase(Observable.error(Throwable("Error!")))

        onView(withId(R.id.edt_url)).perform(typeText("text"))
        onView(withId(R.id.btn_start)).perform(click())

        onView(withText("Error!")).inRoot(withDecorView(not(`is`(getActivity().window.decorView))))
            .check(matches(isDisplayed()))
        onView(withId(R.id.edt_url)).check(matches(allOf(isEnabled(), withText("text"))))
        onView(withId(R.id.btn_start)).check(matches(isEnabled()))
    }

    @Test
    fun givenUseCaseCompletes_shouldEnableControls() {
        givenUseCase(Observable.empty())

        onView(withId(R.id.edt_url)).perform(typeText("text"))
        onView(withId(R.id.btn_start)).perform(click())

        onView(withId(R.id.edt_url)).check(matches(allOf(isEnabled(), withText("text"))))
        onView(withId(R.id.btn_start)).check(matches(isEnabled()))
    }

    private fun givenUseCase(wordsObservable: Observable<List<Word>>) {
        getTestUseCaseDelegate().countWordsObservable = wordsObservable
    }

    private fun getTestUseCaseDelegate(): TestUseCaseDelegate {
        return getTestApp().testUseCaseDelegate
    }

    private fun getTestApp(): TestApp {
        return getActivity().application as TestApp
    }

    private fun getActivity(): Activity {
        return intentsTestRule.activity
    }

    private fun withWord(position: Int, word: Word): Matcher<View> {
        return object : BaseMatcher<View>() {
            override fun describeTo(description: Description?) {
                // does nothing
            }

            override fun matches(any: Any?): Boolean {
                if (any is RecyclerView) {
                    val viewHolder = any.findViewHolderForAdapterPosition(position)

                    if (viewHolder == null) {
                        return false
                    } else {
                        return viewHolder.itemView.let {
                            it.txt_word.text == word.word &&
                                it.txt_count.text == word.count.toString() &&
                                it.progress_bar.visibility == if (word.isStillComputing) View.VISIBLE else View.INVISIBLE
                        }
                    }
                } else {
                    return false
                }
            }
        }
    }
}