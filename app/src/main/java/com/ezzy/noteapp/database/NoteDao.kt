package com.ezzy.noteapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ezzy.noteapp.models.Note
import java.util.concurrent.CompletableFuture

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes ORDER BY created_at DESC")
    fun getAllNotes() : LiveData<List<Note>>

    @Insert
    suspend fun insert(note: Note) : Long

    @Query(
            "UPDATE notes SET title = :title, description = :description, note_color = :noteColor WHERE created_at = :createdAt"
    )
    suspend fun updateNote(
            title: String,
            description: String,
            noteColor: String,
            createdAt: Long
    )

    @Delete
    suspend fun deleteNote(note: Note)

    @Query(
            "SELECT * FROM notes WHERE title LIKE :searchQuery OR description LIKE :searchQuery ORDER BY created_at DESC"
    )
    fun searchNote(searchQuery: String) : LiveData<List<Note>>
}