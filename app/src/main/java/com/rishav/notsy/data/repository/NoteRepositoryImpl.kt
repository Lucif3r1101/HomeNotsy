package com.rishav.notsy.data.repository

import com.rishav.notsy.data.dao.NoteDao
import com.rishav.notsy.data.entity.toDomain
import com.rishav.notsy.data.entity.toEntity
import com.rishav.notsy.domain.model.Note
import com.rishav.notsy.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val dao: NoteDao
) : NoteRepository {
    override suspend fun getAllNotes(): Flow<List<Note>> {
        return dao.getAllNotes().map { noteEntities ->
            noteEntities.map { it.toDomain() }
        }
    }

    override suspend fun getNoteById(id: Int): Note? = dao.getNoteById(id)?.toDomain()

    override suspend fun insertNote(note: Note) = dao.insertNote(note.toEntity())

    override suspend fun deleteNoteById(id: Int) = dao.deleteNoteById(id)
}
