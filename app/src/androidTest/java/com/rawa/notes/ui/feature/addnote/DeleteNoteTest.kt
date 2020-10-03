package com.rawa.notes.ui.feature.addnote

import android.app.Application
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasChildCount
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
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
import com.rawa.notes.db.NoteDo
import com.rawa.notes.domain.Note
import com.rawa.notes.utils.childMatcher
import com.rawa.notes.utils.hasItem
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.anyOf
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@UninstallModules(DatabaseModule::class)
@RunWith(AndroidJUnit4::class)
class DeleteNoteTest {
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

    @Inject
    lateinit var appDatabase: AppDatabase

    private val testNote = Note(
        "Test Note",
        "Test text"
    )

    @Before
    fun init() {
        rule.inject()
        runBlocking {
            appDatabase.noteDao().upsertNote(
                NoteDo(testNote)
            )
        }
    }

    @Test
    fun deleteNote_mainActivity() {
        // Verify existence of test Note
        onView(withId(R.id.rv_main_notes)).check(matches(hasChildCount(1)))
        onView(withId(R.id.rv_main_notes))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
            .check(
                matches(
                    Matchers.allOf(
                        childMatcher(R.id.tv_note_text, 0, withText(testNote.text)),
                        childMatcher(R.id.tv_note_title, 0, withText(testNote.title))
                    )
                )
            )

        // Click on the note
        onView(withId(R.id.rv_main_notes)).perform(
            RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                hasDescendant(withText(testNote.text)), click()
            )
        )

        // Delete note
        onView(withId(R.id.b_detail_delete)).perform(click())

        // Verify note is deleted
        onView(withId(R.id.rv_main_notes)).check(
            matches(
                not(
                    hasItem(
                        hasDescendant(
                            anyOf(
                                withText(testNote.title),
                                withText(testNote.text)
                            )
                        )
                    )
                )
            )
        )
    }
}
