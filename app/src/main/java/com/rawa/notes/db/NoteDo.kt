package com.rawa.notes.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rawa.notes.domain.Note

@Entity(tableName = "Note")
data class NoteDo(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "text") val text: String

) {

    fun toNote(): Note {
        return Note(
            id,
            title,
            text
        )
    }
}
