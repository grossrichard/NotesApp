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
interface RecipeDetailDao {

    @Query("SELECT * FROM recipes WHERE id==:id")
    fun getRecipeDetail(id: String): Recipe?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRecipeDetail(recipe: Recipe)
}