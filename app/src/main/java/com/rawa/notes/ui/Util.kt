package com.rawa.notes.ui

import android.content.Context
import android.util.AttributeSet
import android.util.Xml
import androidx.annotation.LayoutRes

fun Context.getAttr(@LayoutRes resource: Int): AttributeSet {
    val parser = resources.getLayout(resource)
    return Xml.asAttributeSet(parser)
}
