package com.rawa.notes.repository

import com.rawa.notes.domain.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    fun notes(): Flow<List<Note>>
    fun note(id: Int): Flow<Note>
}