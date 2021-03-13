package com.ezzy.noteapp.di

import android.app.Application
import androidx.room.Room
import com.ezzy.noteapp.database.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import javax.inject.Singleton

@Module
//@InstallIn(ApplicationComponent::class)
@InstallIn(ApplicationComponentManager::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        app: Application
    ) = Room.databaseBuilder(app, NoteDatabase::class.java, "note.db")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideDao(database: NoteDatabase) = database.noteDao()

}