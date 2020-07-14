package com.rawa.notes.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.rawa.notes.domain.Note
import java.time.OffsetDateTime

@Entity(tableName = "Note")
@TypeConverters(TimeConverter::class)
data class NoteDo(
    @ColumnInfo
    val title: String,

    @ColumnInfo
    val text: String,

    @ColumnInfo(name = "created_at")
    val createdAt: OffsetDateTime,

    @ColumnInfo(name = "edited_at")
    val lastEditedAt: OffsetDateTime? = null,

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L
) {
    constructor(note: Note) : this(note.title, note.text, note.createdAt, note.lastEditedAt, note.id)

    fun toNote(): Note {
        return Note(
            title,
            text,
            createdAt,
            lastEditedAt,
            id
        )
    }
}
