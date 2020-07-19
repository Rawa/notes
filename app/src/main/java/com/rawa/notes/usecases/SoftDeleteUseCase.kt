package com.rawa.notes.usecases

import com.rawa.notes.domain.Note
import com.rawa.notes.repository.NotesRepository

class SoftDeleteUseCase(private val repo: NotesRepository) {
    suspend fun execute(note: Note, delete: Boolean) {
        return this.execute(note.id, delete)
    }

    suspend fun execute(noteId: Long, delete: Boolean) {
        return repo.softDeleteNote(noteId, delete)
    }
}
