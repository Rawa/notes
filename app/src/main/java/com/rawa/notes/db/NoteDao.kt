package com.rawa.notes.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM Note")
    fun allNotes(): Flow<List<NoteDo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertNote(noteDo: NoteDo)

    @Delete
    suspend fun removeNote(noteDo: NoteDo)
}