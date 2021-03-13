package com.ezzy.noteapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ezzy.noteapp.models.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase(){
    abstract fun noteDao() : NoteDao
}