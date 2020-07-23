package com.rawa.notes.usecases

import com.rawa.notes.repository.GetNoteResult
import com.rawa.notes.repository.NotesRepository
import kotlinx.coroutines.flow.Flow

class NoteUseCase(private val repo: NotesRepository) {
    fun execute(id: Long): Flow<GetNoteResult> {
        return repo.getNote(id)
    }
}
