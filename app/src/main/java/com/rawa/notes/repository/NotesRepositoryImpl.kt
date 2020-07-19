package com.rawa.notes.repository

import com.rawa.notes.db.NoteDao
import com.rawa.notes.db.NoteDo
import com.rawa.notes.domain.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull

class NotesRepositoryImpl(private val noteDao: NoteDao) : NotesRepository {
    override fun notes(): Flow<List<Note>> {
        return noteDao.allNotes().map { notesDo ->
            notesDo.map { it.toNote() }
        }
    }

    override fun getNote(id: Long): Flow<Note> {
        return noteDao.findNote(id).mapNotNull { it?.toNote() }
    }

    override suspend fun addNote(note: Note) {
        noteDao.upsertNote(NoteDo(note))
    }

    override suspend fun removeNote(note: Note) {
        noteDao.removeNote(NoteDo(note))
    }

    override suspend fun updateNote(note: Note) {
        noteDao.updateNote(NoteDo(note))
    }

    override suspend fun softDeleteNote(noteId: Long, softDelete: Boolean) {
        noteDao.softDeleteNote(noteId, softDelete)
    }
}
