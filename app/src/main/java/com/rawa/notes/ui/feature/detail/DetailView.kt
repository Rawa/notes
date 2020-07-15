package com.rawa.notes.ui.feature.detail

import com.rawa.notes.domain.Note

interface DetailView {
    fun noteId(): Long
}

data class DetailViewState(
    val note: Note? = null
)
