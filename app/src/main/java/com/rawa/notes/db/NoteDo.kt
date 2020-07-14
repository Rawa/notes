package com.rawa.notes.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rawa.notes.domain.Note

@Entity(tableName = "Note")
data class NoteDo(
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "text") val text: String,
    @PrimaryKey(autoGenerate = true) val id: Long = 0L
) {
    constructor(note: Note) : this(note.title, note.text, note.id)

    fun toNote(): Note {
        return Note(
            title,
            text,
            id
        )
    }
}
