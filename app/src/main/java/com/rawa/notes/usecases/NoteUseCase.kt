package com.rawa.notes.usecases

import com.rawa.notes.domain.Note
import com.rawa.notes.repository.NotesRepository
import kotlinx.coroutines.flow.Flow

class NoteUseCase(private val repo: NotesRepository) {
    fun execute(id: Long): Flow<Note> {
        return repo.getNote(id)
    }
}
