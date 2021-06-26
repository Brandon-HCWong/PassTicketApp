package com.example.passticketapp

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.passticketapp.TestUtils.clickItemWithId
import com.example.passticketapp.TestUtils.wait
import com.example.passticketapp.TestUtils.waitUntilVisible
import com.example.passticketapp.TestUtils.withRecyclerView
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class TicketAppUITest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(
        MainActivity::class.java
    )

    @Test
    fun testAddTicket() {
        wait(timeout = 1000)
        onView(withId(R.id.button_add))
            .waitUntilVisible()
            .perform(click())
        onView(withId(R.id.radio_button_hour))
            .waitUntilVisible()
            .perform(click())
        onView(withText(R.string.add))
            .waitUntilVisible()
            .perform(click())
        onView(withId(R.id.view_recycler))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(8))
        onView(
            withRecyclerView(R.id.view_recycler).atPositionOnView(8, R.id.text_title)
        ).check(matches(withText("1 Hour Pass")))
        wait(timeout = 1000)
    }

    @Test
    fun testCheckTicketDetail() {
        wait(timeout = 1000)
        onView(withId(R.id.view_recycler))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(1))
            .perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    1,
                    click()
                )
            )
        onView(withId(R.id.layout_detail))
            .waitUntilVisible()
        onView(withId(R.id.text_title))
            .check(matches(withText("3 Day Pass")))
        wait(1000)
    }

    @Test
    fun testActivateTicket() {
        wait(timeout = 1000)
        onView(withId(R.id.view_recycler))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(1))
            .perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    1,
                    clickItemWithId(R.id.button_activate)
                )
            )
        wait(timeout = 1000)
        onView(
            withRecyclerView(R.id.view_recycler).atPositionOnView(1, R.id.button_activate)
        ).check(matches(withText(R.string.activated)))
        wait(timeout = 1000)
    }
}