package com.rawa.notes.ui.feature.detail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.rawa.notes.domain.Note
import com.rawa.notes.ui.feature.Async
import com.rawa.notes.ui.feature.Fail
import com.rawa.notes.ui.feature.Success
import com.rawa.notes.ui.feature.Uninitialized
import com.rawa.notes.usecases.NoteUseCase
import com.rawa.notes.usecases.SoftDeleteUseCase
import com.rawa.notes.usecases.UpdateNoteUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.scan
import timber.log.Timber

@ExperimentalCoroutinesApi
class DetailViewModel @ViewModelInject constructor(
    private val noteUseCase: NoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val softDeleteUseCase: SoftDeleteUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val initialDetailViewState = DetailViewState(null)

    // This is done due to arguments for view models seemingly not being supported currently
    // See: https://github.com/google/dagger/issues/1906
    fun bind(view: DetailView): Flow<Async<DetailViewState>> {
        return merge(
            view.events().flatMapMerge { eventProcessor(it) },
            noteUseCase.execute(view.noteId()).map {
                it.fold({
                    // TODO Handle error
                    Timber.d("Note UseCase error: $it")
                    DetailUpdate.NoteNotFound
                }, {
                    DetailUpdate.NoteUpdate(it.value)
                })
            }
        )
            .scan(initialDetailViewState, { state: DetailViewState, update: DetailUpdate ->
                when (update) {
                    DetailUpdate.NoteNotFound -> {
                        // TODO Currently do nothing, possibly we should navigate up
                        state
                    }
                    is DetailUpdate.NoteUpdate -> state.copy(note = update.note)
                    is DetailUpdate.Navigate -> {
                        view.navigate(update.detailDestination)
                        state
                    }
                }
            })
            .map { Success(it) }
            .onStart { Uninitialized }
            .distinctUntilChanged()
            .catch { Fail(it, null) }
    }

    private suspend fun eventProcessor(action: DetailEvent): Flow<DetailUpdate> {
        return when (action) {
            is DetailEvent.DeleteNote -> delete(action.note)
            is DetailEvent.SaveNote -> save(action.note)
        }
    }

    private suspend fun save(note: Note): Flow<DetailUpdate> {
        updateNoteUseCase.execute(note)

        return flowOf(
            DetailUpdate.Navigate(
                DetailDestination.NoteDeleted(note.id)
            )
        )
    }

    private suspend fun delete(note: Note): Flow<DetailUpdate> {
        softDeleteUseCase.execute(note, true)
        return flowOf(DetailUpdate.Navigate(DetailDestination.Main))
    }
}

private sealed class DetailUpdate {
    data class NoteUpdate(val note: Note) : DetailUpdate()
    object NoteNotFound : DetailUpdate()

    data class Navigate(val detailDestination: DetailDestination) : DetailUpdate()
}
