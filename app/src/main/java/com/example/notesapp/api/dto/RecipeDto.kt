package com.example.notesapp.api.dto

/**
 * Created by Richard Gross on 2020-01-15
 */
data class RecipeDto(
    var name: String? = null,
    var description: String? = null,
    var duration: Int? = null,
    var ingredients: List<String>? = null,
    var info: String? = null,
    var id: String? = null,
    var score: Float? = null
)