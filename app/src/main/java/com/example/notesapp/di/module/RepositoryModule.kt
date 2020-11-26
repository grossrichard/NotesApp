package com.example.notesapp.di.module

import com.example.notesapp.data.NotesLocalSource
import com.example.notesapp.data.NotesRemoteSource
import com.example.notesapp.domain.repository.NotesRepository
import com.example.notesapp.domain.repository.implementation.NotesRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author richardgross on 26/11/2020
 */
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideNotesRepository(
        localSource: NotesLocalSource,
        remoteSource: NotesRemoteSource
    ): NotesRepository = NotesRepositoryImpl(localSource, remoteSource)

}