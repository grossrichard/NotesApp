package com.example.notesapp.infrastructure.api

import com.example.notesapp.infrastructure.api.NotesApiDefinition
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Richard Gross on 2020-01-18
 */

@Singleton
class NotesApiService @Inject constructor(val service: NotesApiDefinition)