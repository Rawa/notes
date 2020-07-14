package com.rawa.notes.usecases

import com.rawa.notes.domain.Note
import com.rawa.notes.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

class NotesUseCase(private val repo: NotesRepository) {
    fun execute(): Flow<List<Note>> {
        Timber.d("Start fetching notes!")
        return repo.notes()
    }
}
