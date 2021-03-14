package com.ezzy.noteapp.repository

import androidx.lifecycle.LiveData
import com.ezzy.noteapp.database.NoteDatabase
import com.ezzy.noteapp.models.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Provider

class NoteRepository @Inject constructor(
    private val noteDatabase: Provider<NoteDatabase>
){
    fun getAllNotes() : LiveData<List<Note>> {
        return noteDatabase.get().noteDao().getAllNotes()
    }

   suspend fun addNote(note: Note){
        noteDatabase.get().noteDao().insert(note)
    }

    suspend fun deleteNote(note: Note){
        noteDatabase.get().noteDao().deleteNote(note)
    }
}