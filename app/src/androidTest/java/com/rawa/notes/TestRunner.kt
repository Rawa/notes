package com.rawa.notes

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication
import timber.log.Timber

class TestRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader,
        className: String,
        context: Context
    ): Application {
        Timber.plant(Timber.DebugTree())
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}
