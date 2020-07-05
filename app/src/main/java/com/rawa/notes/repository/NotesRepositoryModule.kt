package com.rawa.notes.repository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object NotesRepositoryModule {
    @Provides
    fun provideNotesRepository(): NotesRepository {
        return NotesRepositoryImpl()
    }
}
