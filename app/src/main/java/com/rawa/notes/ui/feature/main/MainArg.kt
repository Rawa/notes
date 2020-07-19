package com.rawa.notes.ui.feature.main

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class MainArg : Parcelable

@Parcelize
data class NoteDeleted(val id: Long) : MainArg()
