package com.ezzy.noteapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
class Note(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "note_color") val noteColor: String?,
    @ColumnInfo(name = "created_at") val creationTime: String?,
    @ColumnInfo(name = "update_at") val updateDate: String?
)