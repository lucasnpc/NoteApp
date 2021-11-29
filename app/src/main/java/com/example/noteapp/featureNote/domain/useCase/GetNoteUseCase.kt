package com.example.noteapp.featureNote.domain.useCase

import com.example.noteapp.featureNote.domain.model.Note
import com.example.noteapp.featureNote.domain.repository.NoteRepository

class GetNoteUseCase(private val repository: NoteRepository) {

    suspend operator fun invoke(id: Int): Note? {
        return repository.getNoteById(id)
    }
}