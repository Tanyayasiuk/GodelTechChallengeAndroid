package com.example.tanya.godeltechchallengeandroid.ui.start

import androidx.test.espresso.IdlingRegistry
import io.reactivex.Scheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class RxJavaIdlingResourceTestRule : TestRule {

    override fun apply(base: Statement?, description: Description?): Statement {
        return RxJavaIdlingResourceStatement(base)
    }

    private class RxJavaIdlingResourceStatement(private val statement: Statement?) : Statement() {

        override fun evaluate() {
            RxJavaPlugins.reset()
            RxJavaPlugins.setIoSchedulerHandler(this::wrap)
            RxJavaPlugins.setNewThreadSchedulerHandler(this::wrap)
            RxJavaPlugins.setComputationSchedulerHandler(this::wrap)

            statement?.evaluate()

            RxJavaPlugins.reset()
        }

        private fun wrap(scheduler: Scheduler): Scheduler {
            return DelegatingIdlingResourceScheduler(scheduler, scheduler::class.java.simpleName).apply {
                IdlingRegistry.getInstance().register(this)
            }
        }
    }
}