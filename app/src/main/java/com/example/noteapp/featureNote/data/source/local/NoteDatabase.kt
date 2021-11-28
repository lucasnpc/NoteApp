package com.example.noteapp.featureNote.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteapp.featureNote.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase : RoomDatabase() {

    abstract val noteDao: NoteDao

    companion object{
        const val DATABASE_NAME = "note_db"
    }
}