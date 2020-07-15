package com.rawa.notes.ui.feature.detail

import com.rawa.notes.repository.NotesRepository
import com.rawa.notes.usecases.DeleteNoteUseCase
import com.rawa.notes.usecases.NoteUseCase
import com.rawa.notes.usecases.UpdateNoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class DetailModule {

    @Provides
    fun providesNoteUseCase(
        repository: NotesRepository
    ): NoteUseCase {
        return NoteUseCase(repository)
    }

    @Provides
    fun providesUpdateNoteUseCase(
        repository: NotesRepository
    ): UpdateNoteUseCase {
        return UpdateNoteUseCase(repository)
    }

    @Provides
    fun providesDeleteNoteUseCase(
        repository: NotesRepository
    ): DeleteNoteUseCase {
        return DeleteNoteUseCase(repository)
    }
}
