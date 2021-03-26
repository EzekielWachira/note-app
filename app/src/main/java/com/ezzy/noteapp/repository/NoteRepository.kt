package com.ezzy.noteapp.repository

import androidx.lifecycle.LiveData
import com.ezzy.noteapp.database.NoteDao
import com.ezzy.noteapp.models.Note
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDao: NoteDao
){
    fun getAllNotes() : LiveData<List<Note>> {
        return noteDao.getAllNotes()
    }

   suspend fun addNote(note: Note){
        noteDao.insert(note)
    }

    suspend fun updateNote(
        note: Note
    ) = note.noteColor?.let {noteColor ->
        note.creationTime?.let { creationTime ->
            noteDao.updateNote(
                note.title,
                note.description,
                noteColor,
                    creationTime
        )
        }
    }

    suspend fun deleteNote(note: Note){
        noteDao.deleteNote(note)
    }

    fun searchNotes(searchQuery : String) : LiveData<List<Note>>{
        return noteDao.searchNote(searchQuery)
    }
}