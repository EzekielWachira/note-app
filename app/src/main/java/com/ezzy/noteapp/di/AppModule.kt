package com.ezzy.noteapp.di

import android.content.Context
import androidx.room.Room
import com.ezzy.noteapp.database.NoteDao
import com.ezzy.noteapp.database.NoteDatabase
import com.ezzy.noteapp.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext appContext: Context
    ) =  Room.databaseBuilder(appContext, NoteDatabase::class.java, "note.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideDao(database: NoteDatabase) = database.noteDao()


    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())

    @Provides
    @Singleton
    fun provideRepository(noteDao: NoteDao) = NoteRepository(noteDao)

    

    /*
    * TODO
    *  write a view mode provider
    * */



}