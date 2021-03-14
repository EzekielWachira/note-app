package com.ezzy.noteapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.ezzy.noteapp.database.NoteDatabase
import com.ezzy.noteapp.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext appContext: Context
    ) = Provider {
        Room.databaseBuilder(appContext, NoteDatabase::class.java, "note.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideDao(database: NoteDatabase) = database.noteDao()


    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())

    @Provides
    @Singleton
    fun provideRepository(noteDatabase: Provider<NoteDatabase>) = NoteRepository(noteDatabase)

    /*
    * TODO
    *  write a view mode provider
    * */



}