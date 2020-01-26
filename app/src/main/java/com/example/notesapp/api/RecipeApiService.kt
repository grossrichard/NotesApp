package com.example.notesapp.api

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Richard Gross on 2020-01-18
 */

@Singleton
class RecipeApiService @Inject constructor(val service: RecipeApiDefinition)