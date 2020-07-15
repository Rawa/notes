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
    @Query("SELECT * FROM Note")
    fun allNotes(): Flow<List<NoteDo>>

    @Query("SELECT * FROM Note WHERE id=:id")
    fun findNote(id: Long): Flow<NoteDo?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertNote(noteDo: NoteDo)

    @Update
    suspend fun updateNote(noteDo: NoteDo)

    @Delete
    suspend fun removeNote(noteDo: NoteDo)
}
