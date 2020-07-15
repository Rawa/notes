package com.rawa.notes.usecases

import com.rawa.notes.domain.Note
import com.rawa.notes.repository.NotesRepository

class DeleteNoteUseCase(private val repo: NotesRepository) {
    suspend fun execute(note: Note) {
        return repo.removeNote(note)
    }
}
