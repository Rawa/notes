package com.rawa.notes.usecases

import com.rawa.notes.domain.Note
import com.rawa.notes.repository.NotesRepository
import kotlinx.coroutines.flow.Flow

class NotesUseCase(val repo: NotesRepository) {
    fun execute(): Flow<List<Note>> {
        return repo.notes()
    }
}
