package com.rawa.notes.ui.feature.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rawa.notes.usecases.NotesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainViewModel @ViewModelInject constructor(
    private val notesUseCase: NotesUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _viewState = MutableStateFlow(MainViewState(emptyList()))

    val notes: StateFlow<MainViewState> = _viewState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            notesUseCase.execute().collect { notes ->
                _viewState.value = _viewState.value.copy(
                    notes = notes.map { note -> NotesRow.NoteRow(note) },
                    extraItem = if(notes.isEmpty()) NotesRow.NoItems else null
                )
            }
        }
    }
}
