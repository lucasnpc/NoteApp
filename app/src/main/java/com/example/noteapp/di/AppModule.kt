package com.example.noteapp.di

import android.app.Application
import androidx.room.Room
import com.example.noteapp.featureNote.data.repository.NoteRepositoryImpl
import com.example.noteapp.featureNote.data.source.local.NoteDatabase
import com.example.noteapp.featureNote.domain.repository.NoteRepository
import com.example.noteapp.featureNote.domain.useCase.DeleteNoteUseCase
import com.example.noteapp.featureNote.domain.useCase.GetNotesUseCase
import com.example.noteapp.featureNote.domain.useCase.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase = Room.databaseBuilder(
        app, NoteDatabase::class.java,
        NoteDatabase.DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository = NoteRepositoryImpl(db.noteDao)

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases = NoteUseCases(
        getNotesUseCase = GetNotesUseCase(repository = repository),
        deleteNoteUseCase = DeleteNoteUseCase(repository = repository)
    )
}