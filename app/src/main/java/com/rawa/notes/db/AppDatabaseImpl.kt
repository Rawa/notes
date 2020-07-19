package com.rawa.notes.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NoteDo::class], version = 1, exportSchema = false)
abstract class AppDatabaseImpl : RoomDatabase(), AppDatabase {
    abstract override fun noteDao(): NoteDao
}
