package com.example.notesapp.di.module

import com.example.notesapp.data.NotesLocalSource
import com.example.notesapp.data.NotesLocalSourceImpl
import com.example.notesapp.data.NotesRemoteSource
import com.example.notesapp.data.NotesRemoteSourceImpl
import com.example.notesapp.domain.NotesDataManager
import com.example.notesapp.domain.repository.NotesRepository
import com.example.notesapp.domain.repository.implementation.NotesRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author richardgross on 26/11/2020
 */

@Module
class SourceModule {

    @Singleton
    @Provides
    fun provideNotesLocalSource(dm: NotesDataManager): NotesLocalSource = NotesLocalSourceImpl(dm)

    @Singleton
    @Provides
    fun provideNotesRemoteSource(dm: NotesDataManager): NotesRemoteSource =
        NotesRemoteSourceImpl(dm)
}