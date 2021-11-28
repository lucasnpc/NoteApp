package com.example.noteapp.featureNote.data.repository

import com.example.noteapp.featureNote.data.source.local.NoteDao
import com.example.noteapp.featureNote.domain.model.Note
import com.example.noteapp.featureNote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val dao: NoteDao
) : NoteRepository {

    override fun getNotes(): Flow<List<Note>> = dao.getNotes()

    override suspend fun getNoteById(id: Int): Note? = dao.getNoteById(id = id)

    override suspend fun insertNote(note: Note) = dao.insertNote(note = note)

    override suspend fun deleteNote(note: Note) = dao.deleteNote(note = note)
}