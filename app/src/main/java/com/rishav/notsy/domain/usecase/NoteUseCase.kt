package com.rishav.notsy.domain.usecase

import androidx.compose.runtime.LaunchedEffect
import com.rishav.notsy.domain.model.Note
import com.rishav.notsy.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(): Flow<List<Note>> {
        return noteRepository.getAllNotes()
    }
}

class AddNoteUseCase @Inject constructor(private val repository: NoteRepository) {
    suspend operator fun invoke(note: Note) = repository.insertNote(note)
}

class DeleteNoteUseCase @Inject constructor(private val repository: NoteRepository) {
    suspend operator fun invoke(id: Int) = repository.deleteNoteById(id)
}
