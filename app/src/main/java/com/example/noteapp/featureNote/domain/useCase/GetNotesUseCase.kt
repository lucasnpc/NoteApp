package com.example.noteapp.featureNote.domain.useCase

import com.example.noteapp.featureNote.domain.model.Note
import com.example.noteapp.featureNote.domain.repository.NoteRepository
import com.example.noteapp.featureNote.domain.util.NoteOrder
import com.example.noteapp.featureNote.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesUseCase(private val repository: NoteRepository) {

    operator fun invoke(noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)): Flow<List<Note>> =
        repository.getNotes().map { notes ->
            when (noteOrder.orderType) {
                is OrderType.Ascending -> {
                    sorNotesAscending(noteOrder, notes)
                }
                is OrderType.Descending -> {
                    sortNotesDescending(noteOrder, notes)
                }
            }
        }

    private fun sorNotesAscending(
        noteOrder: NoteOrder,
        notes: List<Note>
    ) = when (noteOrder) {
        is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
        is NoteOrder.Date -> notes.sortedBy { it.timestamp }
        is NoteOrder.Color -> notes.sortedBy { it.color }
    }

    private fun sortNotesDescending(
        noteOrder: NoteOrder,
        notes: List<Note>
    ) = when (noteOrder) {
        is NoteOrder.Title -> notes.sortedByDescending { it.title.lowercase() }
        is NoteOrder.Date -> notes.sortedByDescending { it.timestamp }
        is NoteOrder.Color -> notes.sortedByDescending { it.color }
    }
}