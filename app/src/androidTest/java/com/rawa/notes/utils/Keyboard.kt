package com.rawa.notes.utils

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import java.io.IOException

fun isKeyboardOpen(): Boolean {
    val checkKeyboardCmd = "dumpsys input_method | grep mInputShown"

    try {
        return UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
            .executeShellCommand(checkKeyboardCmd).contains("mInputShown=true")
    } catch (e: IOException) {
        throw RuntimeException("Keyboard check failed", e)
    }
}
