package com.rawa.notes.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf.allOf

fun childMatcher(childId: Int, position: Int, childMatcher: Matcher<View>): Matcher<View> {
    return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
        override fun describeTo(description: Description?) {
            description?.appendText("Matches that childId view exists in itemView for position and applies childMatcher")
        }

        override fun matchesSafely(recyclerView: RecyclerView?): Boolean {
            val viewHolder =
                recyclerView?.findViewHolderForAdapterPosition(position) ?: return false
            return hasDescendant(
                allOf(
                    withId(childId),
                    childMatcher
                )
            ).matches(viewHolder.itemView)
        }
    }
}

fun hasItem(matcher: Matcher<View>): Matcher<View> {
    return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
        override fun describeTo(description: Description) {
            description.appendText("has item: ")
            matcher.describeTo(description)
        }

        override fun matchesSafely(view: RecyclerView): Boolean {
            val adapter = view.adapter
            for (position in 0 until adapter!!.itemCount) {
                val type = adapter.getItemViewType(position)
                val holder = adapter.createViewHolder(view, type)
                adapter.onBindViewHolder(holder, position)
                if (matcher.matches(holder.itemView)) {
                    return true
                }
            }
            return false
        }
    }
}
