package com.ezzy.noteapp.di

import android.app.Application
import androidx.room.Room
import com.ezzy.noteapp.database.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
//@InstallIn(ApplicationComponent::class)
@InstallIn(SingletonComponent::class)
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

    /*
    * TODO
    *  write an application provider
     * */

    /*
    * TODO
    *  write a repository provider
    * */

    /*
    * TODO
    *  write a view mode provider
    * */



}