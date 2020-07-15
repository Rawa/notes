package com.rawa.notes.repository

import com.rawa.notes.domain.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    fun notes(): Flow<List<Note>>
    fun getNote(id: Long): Flow<Note>
    suspend fun addNote(note: Note)
    suspend fun removeNote(note: Note)
}
