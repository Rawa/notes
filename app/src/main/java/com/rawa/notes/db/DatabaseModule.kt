package com.rawa.notes.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    @Named("notes.database.name")
    fun provideDatabaseName(): String {
        return "notes-database"
    }

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context,
        @Named("notes.database.name") databaseName: String
    ): AppDatabase {
        return Room.databaseBuilder(context, AppDatabaseImpl::class.java, databaseName).build()
    }

    @Singleton
    @Provides
    fun provideNotesDao(
        appDatabase: AppDatabase
    ): NoteDao {
        return appDatabase.noteDao()
    }
}
