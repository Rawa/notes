package com.rawa.notes.ui.feature.main

import com.rawa.notes.domain.Note

interface NotesView

sealed class NotesRow {
    data class NoteRow(val note: Note) : NotesRow()
}

data class MainViewState(
    val notes: List<NotesRow.NoteRow>
)
