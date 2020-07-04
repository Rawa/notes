package com.rawa.notes.ui.feature.main

import com.rawa.notes.domain.Note

sealed class NotesRow {
    data class NoteRow(val note: Note): NotesRow()
    object NoItems: NotesRow()
}

