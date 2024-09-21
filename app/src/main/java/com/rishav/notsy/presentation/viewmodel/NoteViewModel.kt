package com.rishav.notsy.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rishav.notsy.domain.model.Note
import com.rishav.notsy.domain.usecase.AddNoteUseCase
import com.rishav.notsy.domain.usecase.DeleteNoteUseCase
import com.rishav.notsy.domain.usecase.GetNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val addNoteUseCase: AddNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {

    private val _notesState = MutableStateFlow<List<Note>>(emptyList())

    val notesState: StateFlow<List<Note>> = _notesState.asStateFlow()


    init {
        loadNotes()
    }

    private fun loadNotes() {
        viewModelScope.launch {
            val notes = getNotesUseCase()
            _notesState.value = notes
        }
    }

    fun addNote(note: Note) = viewModelScope.launch {
        addNoteUseCase(note)
        loadNotes()
    }

    fun deleteNoteById(id: Int) = viewModelScope.launch {
        deleteNoteUseCase(id)
        loadNotes()
    }
}
