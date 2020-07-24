package com.rawa.notes.ui

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.util.Xml
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment

fun Context.getAttr(@LayoutRes resource: Int): AttributeSet {
    val parser = resources.getLayout(resource)
    return Xml.asAttributeSet(parser)
}

fun Activity.hideKeyboard(): Boolean {
    return getSystemService<InputMethodManager>()!!
        .hideSoftInputFromWindow((currentFocus ?: View(this)).windowToken, 0)
}

fun Fragment.hideKeyboard(): Boolean {
    return requireContext().getSystemService<InputMethodManager>()!!
        .hideSoftInputFromWindow((activity?.currentFocus ?: View(context)).windowToken, 0)
}
