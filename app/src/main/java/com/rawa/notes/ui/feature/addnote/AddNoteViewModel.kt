package com.rawa.notes.ui.feature.addnote

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.rawa.notes.domain.Note
import com.rawa.notes.usecases.AddNoteUseCase
import com.rawa.notes.usecases.NoteUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@ExperimentalCoroutinesApi
class AddNoteViewModel @ViewModelInject constructor(
    private val addNoteUseCase: AddNoteUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    suspend fun save(note: Note) {
        addNoteUseCase.execute(note)
    }
}
