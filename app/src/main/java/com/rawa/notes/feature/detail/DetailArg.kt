package com.rawa.notes.feature.detail

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailArg(
    val name: String
) : Parcelable
