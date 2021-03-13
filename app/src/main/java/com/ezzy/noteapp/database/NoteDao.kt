package com.ezzy.noteapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ezzy.noteapp.models.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes ORDER BY created_at DESC")
    fun getAllNotes() : LiveData<List<Note>>

    @Insert
    suspend fun insert(note: Note) : Long

    @Delete
    suspend fun deleteNote(note: Note)
}