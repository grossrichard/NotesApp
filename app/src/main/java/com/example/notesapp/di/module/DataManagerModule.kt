package com.example.notesapp.di.module

import com.example.notesapp.domain.NotesDataManager
import com.example.notesapp.infrastructure.api.NotesApiService
import com.example.notesapp.infrastructure.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author richardgross on 26/11/2020
 */
@Module
class DataManagerModule {

    @Singleton
    @Provides
    fun provideNotesDataManager(apiService: NotesApiService, db: AppDatabase): NotesDataManager =
        NotesDataManager(apiService, db)

}