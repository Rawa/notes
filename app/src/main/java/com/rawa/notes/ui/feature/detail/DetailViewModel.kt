package com.rawa.notes.ui.feature.detail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.rawa.notes.domain.Note
import com.rawa.notes.usecases.NoteUseCase
import com.rawa.notes.usecases.SoftDeleteUseCase
import com.rawa.notes.usecases.UpdateNoteUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@ExperimentalCoroutinesApi
class DetailViewModel @ViewModelInject constructor(
    private val noteUseCase: NoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val softDeleteUseCase: SoftDeleteUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    // TODO Should be a proper lifecycle of a viewmodel and not use a execute method.
    // This is done due to arguments for view models seemingly not being supported currently
    // See: https://github.com/google/dagger/issues/1906
    fun execute(id: Long): Flow<DetailViewState> {
        return noteUseCase.execute(id).map {
            DetailViewState(it)
        }
    }

    suspend fun save(note: Note) {
        updateNoteUseCase.execute(note)
    }

    suspend fun delete(note: Note) {
        softDeleteUseCase.execute(note, true)
    }
}
