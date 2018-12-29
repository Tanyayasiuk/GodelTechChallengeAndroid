package com.example.tanya.godeltechchallengeandroid.ui.main

import android.support.v7.widget.RecyclerView
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.tanya.godeltechchallengeandroid.R
import com.example.tanya.godeltechchallengeandroid.domain.entity.Word
import com.example.tanya.godeltechchallengeandroid.ui.core.IntegrationTest
import kotlinx.android.synthetic.main.item_word_count.view.*
import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.Test

class MainActivityTest : IntegrationTest(MainActivity::class.java) {

    @Test
    fun givenRestServiceReturnsNothing_onTypeUrlAndStartButtonClick_shouldDisableControls() {
        apiMocksController.givenRestServiceReturnsNothing()

        onView(withId(R.id.edt_url)).perform(typeText("text"))
        onView(withId(R.id.btn_start)).perform(click())

        onView(withId(R.id.edt_url)).check(matches(not(isEnabled())))
        onView(withId(R.id.btn_start)).check(matches(not(isEnabled())))
    }

    @Test
    fun givenRestServiceReturnsResult_shouldShowWordsAndEnableControls() {
        val word1 = Word("word1", 1)
        val word2 = Word("word2", 2)
        apiMocksController.givenRestServiceReturnsResult("word1 word2 word2")

        onView(withId(R.id.edt_url)).perform(typeText("text"))
        onView(withId(R.id.btn_start)).perform(click())

        onView(withId(R.id.rv_words_counts)).check(matches(allOf(withWord(0, word2), withWord(1, word1))))
        onView(withId(R.id.edt_url)).check(matches(allOf(isEnabled(), withText("text"))))
        onView(withId(R.id.btn_start)).check(matches(isEnabled()))
    }

    @Test
    fun givenRestServiceReturnsError_shouldEnableControlsAndShowToast() {
        apiMocksController.givenRestServiceReturnsError(Throwable("Error!"))

        onView(withId(R.id.edt_url)).perform(typeText("text"))
        onView(withId(R.id.btn_start)).perform(click())

        assertToast("Error!")
        onView(withId(R.id.edt_url)).check(matches(allOf(isEnabled(), withText("text"))))
        onView(withId(R.id.btn_start)).check(matches(isEnabled()))
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
                                    it.txt_count.text == word.count.toString()
                        }
                    }
                } else {
                    return false
                }
            }
        }
    }
}
