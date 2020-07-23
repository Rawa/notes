package com.rawa.notes.ui.feature.detail

import com.rawa.notes.domain.Note
import kotlinx.coroutines.flow.Flow

interface DetailView {
    fun noteId(): Long
    fun events(): Flow<DetailEvent>
    suspend fun navigate(dest: DetailDestination)
}

sealed class DetailEvent {
    data class DeleteNote(val note: Note) : DetailEvent()
    data class SaveNote(val note: Note) : DetailEvent()
}

data class DetailViewState(
    val note: Note? = null
)

sealed class DetailDestination() {
    object Main : DetailDestination()
    data class NoteDeleted(val id: Long) : DetailDestination()
}
