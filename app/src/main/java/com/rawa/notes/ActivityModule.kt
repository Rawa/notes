package com.rawa.notes

import com.rawa.notes.repository.NotesRepository
import com.rawa.notes.usecases.AddNoteUseCase
import com.rawa.notes.usecases.NoteUseCase
import com.rawa.notes.usecases.NotesUseCase
import com.rawa.notes.usecases.SoftDeleteUseCase
import com.rawa.notes.usecases.UpdateNoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

    @Provides
    fun providesNotesUseCase(
        repository: NotesRepository
    ): NotesUseCase {
        return NotesUseCase(repository)
    }

    @Provides
    fun providesSoftDeleteNoteUseCase(
        repository: NotesRepository
    ): SoftDeleteUseCase {
        return SoftDeleteUseCase(repository)
    }

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
    fun providesAddNoteUseCase(
        repository: NotesRepository
    ): AddNoteUseCase {
        return AddNoteUseCase(repository)
    }
}
