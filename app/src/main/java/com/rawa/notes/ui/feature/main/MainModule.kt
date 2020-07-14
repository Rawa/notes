package com.rawa.notes.ui.feature.main

import com.rawa.notes.repository.NotesRepository
import com.rawa.notes.usecases.NotesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class MainModule {

    @Provides
    fun providesNotesUseCase(
        repository: NotesRepository
    ): NotesUseCase {
        return NotesUseCase(repository)
    }
}
