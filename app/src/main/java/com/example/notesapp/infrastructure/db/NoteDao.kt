package com.example.notesapp.infrastructure.db

import androidx.room.*
import com.example.notesapp.data.entity.NoteDbo
import io.reactivex.Single

/**
 * Created by Richard Gross on 2020-01-25
 */

@Dao
interface NoteDao {

    @Query("SELECT * FROM notedbo")
    fun getAll(): Single<List<NoteDbo>>

    @Insert
    fun insert(note: NoteDbo)

    @Query("DELETE FROM notedbo WHERE id = :id")
    fun delete(id: Long)

    @Update
    fun update(note: NoteDbo)

    @Query("SELECT * FROM notedbo WHERE id=:id")
    fun findById(id: Long): Single<NoteDbo>
}