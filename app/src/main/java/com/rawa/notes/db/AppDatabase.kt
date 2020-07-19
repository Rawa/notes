package com.rawa.notes.db

interface AppDatabase {
    fun noteDao(): NoteDao
}
