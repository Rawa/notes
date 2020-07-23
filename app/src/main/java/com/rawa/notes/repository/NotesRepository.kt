package com.rawa.notes.repository

import arrow.core.Either
import com.rawa.notes.domain.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    fun notes(): Flow<List<Note>>
    fun getNote(id: Long): Flow<Either<GetNoteError, GetNoteSuccess>>
    suspend fun addNote(note: Note)
    suspend fun removeNote(note: Note)
    suspend fun updateNote(note: Note)
    suspend fun softDeleteNote(noteId: Long, softDelete: Boolean)
}

typealias GetNoteResult = Either<GetNoteError, GetNoteSuccess>

sealed class GetNoteError {
    object NoteNotFound : GetNoteError()
}

data class GetNoteSuccess(val value: Note)
