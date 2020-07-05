package com.rawa.notes.repository

import com.rawa.notes.domain.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

// Sample Notes repository
class NotesRepositoryImpl() : NotesRepository {
    private val notes = listOf(
        Note(1, "This is a title", "This is some text"),
        Note(2, "This is a title", "This is some text"),
        Note(3, "This is a title", "This is some text"),
        Note(4, "This is a title", "This is some text")
    )

    override fun notes(): Flow<List<Note>> {
        return flowOf(
            notes
        )
    }

    override fun note(id: Int): Flow<Note> {
        return flow {
            notes.firstOrNull { id == it.id }
        }
    }
}
