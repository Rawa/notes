package com.rawa.notes.ui.feature.addnote

import android.app.Application
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasChildCount
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rawa.notes.MainActivity
import com.rawa.notes.R
import com.rawa.notes.db.AppDatabase
import com.rawa.notes.db.AppDatabaseImpl
import com.rawa.notes.db.DatabaseModule
import com.rawa.notes.db.NoteDao
import com.rawa.notes.utils.childMatcher
import com.rawa.notes.utils.isKeyboardOpen
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import javax.inject.Singleton
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@UninstallModules(DatabaseModule::class)
@RunWith(AndroidJUnit4::class)
class AddNoteTest {
    @get:Rule
    val rule = HiltAndroidRule(this)

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Module
    @InstallIn(ApplicationComponent::class)
    object TestModule {
        @Singleton
        @Provides
        fun provideAppDatabase(
            app: Application
        ): AppDatabase {
            return Room.inMemoryDatabaseBuilder(app, AppDatabaseImpl::class.java).build()
        }

        @Singleton
        @Provides
        fun provideNotesDao(
            appDatabase: AppDatabase
        ): NoteDao {
            return appDatabase.noteDao()
        }
    }

    @Before
    fun init() {
        rule.inject()
    }

    @Test
    fun addNote_mainActivity() {
        // Type text and then press the button.
        onView(withId(R.id.fab_main_addnote))
            .perform(click())

        val title = "Note Title"
        val text = "Lorem Ipsum"

        // Insert data
        onView(withId(R.id.tiet_addnote_title))
            .perform(typeText(title))
        onView(withId(R.id.tiet_addnote_text))
            .perform(typeText(text))

        // Save
        onView(withId(R.id.b_addnote_save))
            .perform(click())

        // Verify existence of new Note
        onView(withId(R.id.rv_main_notes)).check(matches(hasChildCount(1)))
        onView(withId(R.id.rv_main_notes))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
            .check(
                matches(
                    Matchers.allOf(
                        childMatcher(R.id.tv_note_text, 0, withText(text)),
                        childMatcher(R.id.tv_note_title, 0, withText(title))
                    )
                )
            )

        // Ensure keyboard is closed
        Assert.assertFalse(isKeyboardOpen())
    }
}
