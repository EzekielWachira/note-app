package com.ezzy.noteapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "note_color") val noteColor: String? = "#FFFFFF",
    @ColumnInfo(name = "created_at") val creationTime: Long? = System.currentTimeMillis()
) : Serializable