package com.ezzy.noteapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ezzy.noteapp.models.Note
import com.ezzy.noteapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    app: Application,
    val noteRepository: NoteRepository
) : AndroidViewModel(app) {

    fun insertNote(note: Note) {
        viewModelScope.launch {
            noteRepository.addNote(note)
        }
    }

    fun getAllNotes() = noteRepository.getAllNotes()

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }
    }

    fun searchNotes(searchQuery : String) = noteRepository.searchNotes(searchQuery)


}