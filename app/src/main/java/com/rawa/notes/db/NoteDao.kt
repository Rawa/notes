package com.rawa.notes.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM Note WHERE soft_deleted=0 AND archived=0")
    fun allNotes(): Flow<List<NoteDo>>

    @Query("SELECT * FROM Note WHERE id=:id")
    fun findNote(id: Long): Flow<NoteDo?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertNote(noteDo: NoteDo)

    @Update
    suspend fun updateNote(noteDo: NoteDo)

    @Delete
    suspend fun removeNote(noteDo: NoteDo)

    @Query("UPDATE Note SET archived = :archive WHERE id=:noteId")
    suspend fun archiveNote(noteId: Long, archive: Boolean): Int

    @Query("UPDATE Note SET soft_deleted = :softDelete WHERE id=:noteId")
    suspend fun softDeleteNote(noteId: Long, softDelete: Boolean): Int
}
