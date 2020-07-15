package com.rawa.notes.usecases

import com.rawa.notes.domain.Note
import com.rawa.notes.repository.NotesRepository
import kotlinx.coroutines.flow.Flow

class UpdateNoteUseCase(private val repo: NotesRepository) {
    suspend fun execute(note: Note) {
        return repo.updateNote(note)
    }
}
