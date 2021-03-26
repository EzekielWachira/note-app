package com.ezzy.noteapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ezzy.noteapp.models.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase(){
    abstract fun noteDao() : NoteDao

//    var MIGRATION_1_2 : Migration = object : Migration(1, 2){
//        override fun migrate(database: SupportSQLiteDatabase) {
//            database.execSQL(
//                    "ALTER TABLE notes RENAME  "
//            )
//        }
//    }
}