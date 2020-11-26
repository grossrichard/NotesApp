package com.example.notesapp.di.module

import com.example.notesapp.domain.repository.NotesRepository
import com.example.notesapp.domain.usecase.CreateNoteUseCase
import com.example.notesapp.domain.usecase.DeleteNoteUseCase
import com.example.notesapp.domain.usecase.LoadNoteDetailUseCase
import com.example.notesapp.domain.usecase.LoadNotesUseCase
import com.example.notesapp.domain.usecase.implementation.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author richardgross on 26/11/2020
 */
@Module
class UseCaseModule {

    @Singleton
    @Provides
    fun provideCreateNoteUseCase(repo: NotesRepository): CreateNoteUseCase =
        CreateNoteUseCaseImpl(repo)

    @Singleton
    @Provides
    fun provideLoadNotesUseCase(repo: NotesRepository): LoadNotesUseCase =
        LoadNotesUseCaseImpl(repo)

    @Singleton
    @Provides
    fun provideLoadNoteDetailUseCase(repo: NotesRepository): LoadNoteDetailUseCase =
        LoadNoteDetailUseCaseImpl(repo)

    @Singleton
    @Provides
    fun provideUpdateNoteUseCase(repo: NotesRepository): UpdateNoteUseCaseImpl =
        UpdateNoteUseCaseImpl(repo)

    @Singleton
    @Provides
    fun provideDeleteNoteUseCase(repo: NotesRepository): DeleteNoteUseCase =
        DeleteNoteUseCaseImpl(repo)

}