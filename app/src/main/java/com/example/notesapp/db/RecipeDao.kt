package com.example.notesapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notesapp.entity.Recipe

/**
 * Created by Richard Gross on 2020-01-25
 */

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipes")
    fun getAll(): List<Recipe>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAll(recipes: List<Recipe>)
}