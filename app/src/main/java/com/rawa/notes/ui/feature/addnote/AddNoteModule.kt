package com.rawa.notes.ui.feature.addnote

import com.rawa.notes.repository.NotesRepository
import com.rawa.notes.usecases.AddNoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class AddNoteModule {

    @Provides
    fun providesAddNoteUseCase(
        repository: NotesRepository
    ): AddNoteUseCase {
        return AddNoteUseCase(repository)
    }
}
