package com.rishav.notsy.domain.repository

import com.rishav.notsy.domain.model.Note

interface NoteRepository {
    suspend fun getAllNotes(): List<Note>
    suspend fun getNoteById(id: Int): Note?
    suspend fun insertNote(note: Note)
    suspend fun deleteNoteById(id: Int)
}
