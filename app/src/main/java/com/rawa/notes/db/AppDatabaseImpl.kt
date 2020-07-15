package com.rawa.notes.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NoteDo::class], version = 1)
abstract class AppDatabaseImpl : RoomDatabase(), AppDatabase {
    abstract override fun noteDao(): NoteDao
}

