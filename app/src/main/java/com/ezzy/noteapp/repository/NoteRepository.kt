package com.ezzy.noteapp.repository

import androidx.lifecycle.LiveData
import com.ezzy.noteapp.database.NoteDao
import com.ezzy.noteapp.database.NoteDatabase
import com.ezzy.noteapp.models.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Provider

class NoteRepository @Inject constructor(
    private val noteDao: NoteDao
){
    fun getAllNotes() : LiveData<List<Note>> {
        return noteDao.getAllNotes()
    }

   suspend fun addNote(note: Note){
        noteDao.insert(note)
    }

    suspend fun deleteNote(note: Note){
        noteDao.deleteNote(note)
    }

    fun searchNotes(searchQuery : String) : LiveData<List<Note>>{
        return noteDao.searchNote(searchQuery)
    }
}